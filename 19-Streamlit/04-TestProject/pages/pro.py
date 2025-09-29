import streamlit as st
from langchain_core.messages.chat import ChatMessage
from dotenv import load_dotenv
from langchain_core.messages.chat import ChatMessage
from langchain_core.prompts import ChatPromptTemplate
from langchain_openai import ChatOpenAI
from langchain_core.output_parsers import StrOutputParser

from langchain_teddynote.prompts import load_prompt
from langchain import hub

import glob
import os

from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain_community.document_loaders import PyMuPDFLoader
from langchain_community.document_loaders import UnstructuredExcelLoader
from langchain_community.vectorstores import FAISS
from langchain_core.output_parsers import StrOutputParser
from langchain_core.runnables import RunnablePassthrough
from langchain_core.prompts import PromptTemplate
from langchain_openai import ChatOpenAI, OpenAIEmbeddings


load_dotenv()
st.title("hi test file")

# 사용자의 입력을 받는다
user_input = st.chat_input("궁금한 내용을 물어보세요")

# 경고 메세지를 띄우기 위한 빈 영역
# 테스트 엑셀 파일을 올리지 않은경우 작동하게import streamlit as st
from langchain_core.messages.chat import ChatMessage
from dotenv import load_dotenv
from langchain_core.messages.chat import ChatMessage
from langchain_core.prompts import ChatPromptTemplate
from langchain_openai import ChatOpenAI
from langchain_core.output_parsers import StrOutputParser

from langchain_teddynote.prompts import load_prompt
from langchain import hub

import glob
import os

from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain_community.document_loaders import PyMuPDFLoader
from langchain_community.vectorstores import FAISS
from langchain_core.output_parsers import StrOutputParser
from langchain_core.runnables import RunnablePassthrough
from langchain_core.prompts import PromptTemplate
from langchain_openai import ChatOpenAI, OpenAIEmbeddings

warning_msg = st.empty()

if not os.path.exists(".cache"):
    os.mkdir(".cache")
if not os.path.exists(".cache/testEmbeddings"):
    os.mkdir(".cache/testEmbeddings")
if not os.path.exists(".cache/testFiles"):
    os.mkdir(".cache/testFiles")

if "messages" not in st.session_state:
    st.session_state["messages"] = []

if "chain" not in st.session_state:
    st.session_state["chain"] = None

with st.sidebar:
    clear_btn = st.button("대화 초기화")
    st.page_link("https://www.ssg.com/", label="Google", icon="🌎")
    uploaded_file = st.file_uploader("Choose a file", type=["pdf", "xlsx"])
    selected_prompt = "prompts/pdf-rag.yaml"

    # model 선택 메뉴
    selected_model = st.selectbox(
        "LLM 선택", ["gpt-4o", "gpt-4-turbo", "gpt-4o-mini"], index=0
    )


def print_messages():
    for chat_message in st.session_state["messages"]:
        st.chat_message(chat_message.role).write(chat_message.content)


def add_messages(role, message):
    st.session_state["messages"].append(ChatMessage(role=role, content=message))


import logging
import openpyxl as op
from langchain_community.document_loaders import UnstructuredExcelLoader


# FILE이 업로드 되었을때
@st.cache_resource(show_spinner="upload....")
def embed_file(file):

    file_content = file.read()
    file_path = f"./.cache/testFiles/{file.name}"

    with open(file_path, "wb") as f:
        f.write(file_content)

    # file 확장자
    path, ext = os.path.splitext(file.name)

    if ext == ".pdf":
        # 단계 1: 문서 로드(Load Documents)
        # loader = PyMuPDFLoader("data/SPRI_AI_Brief_2023년12월호_F.pdf")
        loader = PyMuPDFLoader(file_path)
        docs = loader.load()

        # 단계 2: 문서 분할(Split Documents)
        text_splitter = RecursiveCharacterTextSplitter(
            chunk_size=1000, chunk_overlap=50
        )
        split_documents = text_splitter.split_documents(docs)

        # 단계 3: 임베딩(Embedding) 생성
        embeddings = OpenAIEmbeddings()

        # 단계 4: DB 생성(Create DB) 및 저장
        # 벡터스토어를 생성합니다.
        vectorstore = FAISS.from_documents(
            documents=split_documents, embedding=embeddings
        )

        # 단계 5: 검색기(Retriever) 생성
        # 문서에 포함되어 있는 정보를 검색하고 생성합니다.
        retriever = vectorstore.as_retriever()
        pass

    elif ext == ".xlsx":
        # UnstructuredExcelLoader 생성
        loader = UnstructuredExcelLoader(file_path, mode="elements")

        # 문서 로드
        docs = loader.load()

        # 문서 길이 출력
        # print(len(docs))

        # # xls인 경우 pandas
        # # df = pd.read_excel(file.name)
        # # xlsx인 경우 openpyxl
        # excel = op.load_workbook(file_path)
        # excel_ws = excel["info"]
        # A1 = excel_ws["A1"]

        # # 행의 값을 숫자로
        # print(A1.row)
        # # 열의 값을 숫자로 (A = 1)
        # print(A1.column)
        # # A1에 들어있는 값
        # print(A1.value)
        # # A1이 어느 위치에 있는지
        # print(A1.coordinate)
        # # 이렇게 여러 범위로도 접근할 수 있다.
        # # range_excel = excel_ws["A1":"C3"]

        text_splitter = RecursiveCharacterTextSplitter(
            chunk_size=1000, chunk_overlap=50
        )
        split_documents = text_splitter.split_documents(docs)
        embeddings = OpenAIEmbeddings()
        vectorstore = FAISS.from_documents(
            documents=split_documents, embedding=embeddings
        )
        retriever = vectorstore.as_retriever()
        pass

    return retriever


def create_chain(retriever, model_name="gpt-4o"):

    prompt = load_prompt("prompts/pdf-rag.yaml", encoding="utf-8")
    # llm = ChatOpenAI(model_name="gpt-4o", temperature=0)
    # output_parser = StrOutputParser()
    # chain = prompt | llm | output_parser

    # 단계 6: 프롬프트 생성(Create Prompt)
    # # 프롬프트를 생성합니다.
    # prompt = PromptTemplate.from_template(
    #     """You are an assistant for question-answering tasks.
    # Use the following pieces of retrieved context to answer the question.
    # If you don't know the answer, just say that you don't know.
    # Answer in Korean.

    # #Context:
    # {context}

    # #Question:
    # {question}

    # #Answer:"""
    # )

    # 단계 7: 언어모델(LLM) 생성
    # 모델(LLM) 을 생성합니다.
    llm = ChatOpenAI(model_name=model_name, temperature=0)

    # 단계 8: 체인(Chain) 생성
    chain = (
        {"context": retriever, "question": RunnablePassthrough()}
        | prompt
        | llm
        | StrOutputParser()
    )

    return chain


# 파일이 업로드 됐을때 처리
if uploaded_file:
    # 파일 업로드 후 retriever 생성(작업시간이 오래 걸릴 예정)
    retriever = embed_file(uploaded_file)
    chain = create_chain(retriever, model_name=selected_model)
    st.session_state["chain"] = chain


if clear_btn:
    st.session_state["messages"] = []

print_messages()

if user_input:

    # chain = create_chain(selected_prompt)

    chain = st.session_state["chain"]

    if chain is not None:
        st.chat_message("user").write(user_input)
        res = chain.stream(user_input)
        with st.chat_message("assistant"):
            # 빈 공간(컨테이너)를 만들어서, 여기레 토큰을 스티리밍 출력한다.
            # 이 공간에 토큰을 찍어서 넣어줌
            container = st.empty()

            answer = ""
            for token in res:
                answer += token
                container.markdown(answer)

        # ChatMessage을 함수로!
        add_messages("user", user_input)
        add_messages("assistant", answer)
    else:
        warning_msg.error("파일을 업로드 해주세요.")
