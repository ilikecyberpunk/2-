from collections import Counter
import re


# 알파벳 26개를 배열로 저장
ALPHABET = [chr(ord("a") + i) for i in range(26)]


def analyze_letters(sentence):
    """문장에서 알파벳별 사용 횟수를 계산한다."""
    counts = [0] * 26  # 과제 요구사항: 알파벳별 등장 횟수를 배열에 저장

    for ch in sentence.lower():
        if "a" <= ch <= "z":
            index = ord(ch) - ord("a")
            counts[index] += 1

    return counts


def sorted_letter_counts(counts):
    """사용 빈도가 높은 순서대로 알파벳을 정렬해 반환한다."""
    pairs = list(zip(ALPHABET, counts))  # 두 배열을 결합
    pairs.sort(key=lambda item: (-item[1], item[0]))
    return pairs


def show_analysis(sentence):
    """전체 알파벳 분석 결과를 출력한다."""
    counts = analyze_letters(sentence)
    sorted_counts = sorted_letter_counts(counts)
    total = sum(counts)

    print("\n[문장 분석 결과]")
    print(f"알파벳 사용 횟수: {total}회")

    for letter, count in sorted_counts:
        print(f"{letter} : {count}")

    nonzero = [(letter, count) for letter, count in sorted_counts if count > 0]

    if nonzero:
        max_count = nonzero[0][1]
        most_used = [letter for letter, count in nonzero if count == max_count]
        print(f"\n가장 많이 사용된 문자: {', '.join(most_used)} ({max_count}회)")
    else:
        print("\n알파벳이 입력되지 않았습니다.")


def search_character(sentence):
    """사용자가 입력한 특정 알파벳의 사용 횟수를 출력한다."""
    while True:
        ch = input("검색할 문자(알파벳 1개, 0은 취소): ").strip().lower()

        if ch == "0":
            return

        if len(ch) == 1 and "a" <= ch <= "z":
            counts = analyze_letters(sentence)
            index = ord(ch) - ord("a")
            print(f"{ch} 문자는 총 {counts[index]}회 사용되었습니다.")
            return

        print("영문 알파벳 한 글자만 입력하세요.")


def tokenize_words(sentence):
    """문장에서 영문 단어만 추출해 소문자로 변환한다."""
    return re.findall(r"[a-zA-Z]+", sentence.lower())


def analyze_words(sentence):
    """단어별 사용 횟수를 계산한다."""
    words = tokenize_words(sentence)

    # 과제의 배열 사용 요구를 만족하도록 단어와 빈도를 별도 배열에 저장
    word_array = []
    count_array = []

    for word in words:
        if word in word_array:
            index = word_array.index(word)
            count_array[index] += 1
        else:
            word_array.append(word)
            count_array.append(1)

    pairs = list(zip(word_array, count_array))
    pairs.sort(key=lambda item: (-item[1], item[0]))

    print("\n[단어 빈도 분석]")
    if not pairs:
        print("분석할 영문 단어가 없습니다.")
        return

    for word, count in pairs:
        print(f"{word} : {count}")

    top_count = pairs[0][1]
    top_words = [word for word, count in pairs if count == top_count]
    print(f"\n가장 많이 사용된 단어: {', '.join(top_words)} ({top_count}회)")


def show_top_letters(sentence):
    """상위 N개의 문자 사용 빈도를 보여준다. (추가 기능)"""
    counts = analyze_letters(sentence)
    pairs = [(letter, count) for letter, count in sorted_letter_counts(counts) if count > 0]

    if not pairs:
        print("\n분석할 알파벳이 없습니다.")
        return

    while True:
        try:
            n = int(input("상위 몇 개를 볼까요? (1~26): "))
            if 1 <= n <= 26:
                break
            print("1부터 26 사이의 숫자를 입력하세요.")
        except ValueError:
            print("정수를 입력하세요.")

    print(f"\n[상위 {n}개 문자]")
    for rank, (letter, count) in enumerate(pairs[:n], start=1):
        print(f"{rank}. {letter} : {count}")


def show_statistics(sentence):
    """문장의 여러 통계 정보를 보여준다. (추가 기능)"""
    letters_only = [ch for ch in sentence if ch.isalpha() and ch.isascii()]
    words = tokenize_words(sentence)
    spaces = sum(ch.isspace() for ch in sentence)
    special_chars = sum(
        not ch.isalnum() and not ch.isspace()
        for ch in sentence
    )

    print("\n[문장 통계]")
    print(f"전체 입력 문자 수 : {len(sentence)}")
    print(f"영문 알파벳 수    : {len(letters_only)}")
    print(f"단어 수            : {len(words)}")
    print(f"공백 수            : {spaces}")
    print(f"특수문자 수        : {special_chars}")


def main():
    print("===================================")
    print("       단어/문자 빈도 분석 프로그램")
    print("===================================")

    sentence = input("분석할 영문 문장을 입력하세요:\n> ")

    while True:
        print("\n-----------------------------------")
        print("1. 문장 분석")
        print("2. 특정 문자 검색")
        print("3. 단어 빈도 분석")
        print("4. 상위 N개 문자 보기")
        print("5. 문장 통계 보기")
        print("9. 프로그램 종료")
        print("-----------------------------------")

        choice = input("선택: ").strip()

        if choice == "1":
            show_analysis(sentence)
        elif choice == "2":
            search_character(sentence)
        elif choice == "3":
            analyze_words(sentence)
        elif choice == "4":
            show_top_letters(sentence)
        elif choice == "5":
            show_statistics(sentence)
        elif choice == "9":
            print("프로그램을 종료합니다.")
            break
        else:
            print("잘못된 메뉴입니다. 다시 선택하세요.")


if __name__ == "__main__":
    main()