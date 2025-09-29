import streamlit as st
from langchain_core.messages.chat import ChatMessage
from dotenv import load_dotenv
from langchain_core.messages.chat import ChatMessage
from langchain_core.prompts import ChatPromptTemplate
from langchain_openai import ChatOpenAI
from langchain_core.output_parsers import StrOutputParser

# 2 prompt 여러개 사용할때
from langchain_teddynote.prompts import load_prompt
from langchain import hub

# 3 동적으로 만들기
import glob

# API KEY 정보로드
load_dotenv()

from langchain_teddynote import logging

logging.langsmith("daun_test_project")

st.title("TEST1234")

# 사용자의 입력을 받는다
user_input = st.chat_input("궁금한 내용을 물어보세요")

# 5. 처음 한번만 실행하기 위한 코드 -> 이걸 안하면 session도 계속해서 초기화 됨. 의미가 없음.
if "messages" not in st.session_state:
    # 4. 대화를 저장하기 위한 용도
    st.session_state["messages"] = []

# 사이드바 생성
with st.sidebar:
    clear_btn = st.button("대화 초기화")

    # 2 여러개의 prompt를 사용하기 위해 추가
    # selected_prompt = st.selectbox(
    #    "프롬프트를 선택해주세요",
    #    ("basic", "sns", "요약"),
    # 0번인 basic을 default로 사용하겠다
    #    index=0,
    # )

    # 3 동적으로 만들기 위해

    prompt_files = glob.glob("prompts/*.yaml")
    selected_prompt = st.selectbox(
        "프롬프트를 선택해주세요",
        prompt_files,
        # 0번인 basic을 default로 사용하겠다
        index=0,
    )
    # 4 변수가 2개인 taml 파일을 위한 세팅
    task_input = st.text_input("TASK 입력", "")


# 6. 이걸 반영하지 않는다면 session에 저장은 하지만 차례대로 모든걸 보여주지 않음. 시각적으로는 session에 저장하지 않을때와 같은 결과
def print_messages():
    for chat_message in st.session_state["messages"]:
        # question1
        st.chat_message(chat_message.role).write(chat_message.content)
    # question2
    # st.chat_message(f"{chat_message.role}:{chat_message.content}")
    # st.chat_message(role).write(message)


# question1, question2
# 두 코드 스니펫은 Streamlit 라이브러리의 st.chat_message를 사용하여 채팅 메시지를 출력하는 방법을 보여줍니다. 그러나 두 방식은 출력 형식과 사용법에서 차이가 있습니다.

# st.chat_message(chat_message.role).write(chat_message.content):
# 이 코드 스니펫은 chat_message.role에 해당하는 역할(예: "user" 또는 "assistant")에 맞춰 채팅 메시지를 출력합니다.
# write() 메서드를 사용하여 chat_message.content를 출력합니다.
# 메시지가 역할에 맞는 형식으로 출력됩니다.
# st.chat_message(f"{chat_message.role}:{chat_message.content}"):
# 이 코드 스니펫은 chat_message.role과 chat_message.content를 문자열로 결합하여 출력합니다.
# 역할과 내용이 함께 출력되며, 형식은 "<role>:<content>"입니다.
# st.chat_message 메서드가 문자열을 직접 출력하는 방식으로 사용됩니다.

# 두 코드의 차이점은 출력 형식에서 발생하며, 첫 번째 방법은 역할에 따른 형식을 보다 잘 유지할 수 있습니다. 두 번째 방법은 내용이 문자열로 결합되어 출력되기 때문에 조금 더 단순한 출력 형식을 제공합니다.


def add_messages(role, message):
    st.session_state["messages"].append(ChatMessage(role=role, content=message))


# 1 chain 생성
# 하나의 prompt만 사용할때
# def create_chain():
# prompt | llm | output_parser
#    prompt = ChatPromptTemplate.from_messages(
#        [
#            ("system", "당신은 친절한 AI 어시스턴트입니다."),
#            ("user", "#Question:\n{question}"),
#        ]
#    )
#    llm = ChatOpenAI(model_name="gpt-4o", temperature=0)
#    output_parser = StrOutputParser()

#    chain = prompt | llm | output_parser
#    return chain


# 2 여러개의 prompt를 사용할때
# def create_chain(prompt_type):

# 기본이 basic이니 if 삭제하고 기본 prompt 작성
# if prompt_type == "basic":
#    pass

# prompt | llm | output_parser
# prompt = ChatPromptTemplate.from_messages(
#    [
#        ("system", "당신은 친절한 AI 어시스턴트입니다."),
#        ("user", "#Question:\n{question}"),
#    ]
# )

# basic if를 삭제하면서 sns가 if절이됨
# if prompt_type == "sns":
# 이전에 공부했던 여러 prompt를 파일로 가지고 오는 부분 해보기
# pass
#    prompt = load_prompt("prompts/sns.yaml", encoding="utf-8")
# elif prompt_type == "요약":
# pass
# 요약 프럼프트를 가지고 옴
#    prompt = hub.pull("teddynote/chain-of-density-korean:946ed62d")

# llm = ChatOpenAI(model_name="gpt-4o", temperature=0)
# output_parser = StrOutputParser()

# chain = prompt | llm | output_parser
# return chain


# 3 동적으로 create_chain 만들기
# 3 def create_chain(prompt_filepath):
# 4 다른 yaml 파일은 변수가 하나지만, prompt-maker.yaml은 변수가 2개! 이를 반영하기
def create_chain(prompt_filepath, task=""):
    # prompt | llm | output_parser
    # prompt = load_prompt("prompts/sns.yaml", encoding="utf-8")
    prompt = load_prompt(prompt_filepath, encoding="utf-8")
    # 4 변수가 여러개인 yaml 파일을 위한 세팅으로 task가 들어오면 task를 세팅한다는 작업
    if task:
        prompt = prompt.partial(task=task)

    llm = ChatOpenAI(model_name="gpt-4o", temperature=0)
    output_parser = StrOutputParser()

    chain = prompt | llm | output_parser

    return chain


# 초기화 버튼이 눌리면
if clear_btn:
    st.session_state["messages"] = []

print_messages()


# 만약에 사용자가 입력이 들어오면...
if user_input:
    # 1. 글씨만 보임
    # st.write(f"사용자 입력: {user_input}")
    # 2. 칸을 만들어줘서 조금 더 낫다
    st.chat_message("user").write(user_input)
    # 3. 똑같은 것을 반복해준다
    # chain을 넣어줌

    # 하나의 prompt만 사용할때
    # chain = create_chain()
    # 여러개의 prompt를 사용할때
    # 선택된 체인 꼭 넣기
    # 4 변수가 여러개인 yaml 파일을 위한 세팅
    chain = create_chain(selected_prompt, task=task_input)

    # invoke 버전
    # answer = chain.invoke({"question": user_input})
    # st.chat_message("assistant").write(answer)
    # stream 버전
    res = chain.stream({"question": user_input})
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
    # ChatMessage(role="user", content=user_input)
    # ChatMessage(role="assistant", content=user_input)

    # st.session_state["messages"].append(("user", user_input))
    # st.session_state["messages"].append(("assistant", user_input))


# 이렇게만 하면 한번 입력할때마다 끝이나기때문에 계속해서 페이지가 다시시작해서 한 글자씩만 계속해서 보임. 대화형식으로 보이지 않음
# 그러므로 이 대화들을 새로고침이 일어나도 가지고 있을 수 있게 저장을 해줘야함!
# 그렇기에 4번을 진행!
