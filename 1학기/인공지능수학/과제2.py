import numpy as np

def validate_inputs(a, b):
    if len(a) != len(b):
        print("오류: 두 리스트의 크기가 서로 다릅니다.")
        return False
    for item in a + b:
        if not isinstance(item, (int, float)):
            print(f"오류: 숫자가 아닌 문자 '{item}'이(가) 포함되어 있습니다.")
            return False
    return True

def cosine_sim(a, b):
    if not validate_inputs(a, b): return None
    v1, v2 = np.array(a), np.array(b)
    norm_a, norm_b = np.linalg.norm(v1), np.linalg.norm(v2)
    result = 0.0 if norm_a == 0 or norm_b == 0 else np.dot(v1, v2) / (norm_a * norm_b)
    return result

def pearson_corr(a, b):
    if not validate_inputs(a, b): return None
    return np.corrcoef(a, b)[0, 1]

data = {
    "D1": [0.82, 0.66, 0.51, 0.35, 0.75, 0.52, 0.62, 0.57],
    "D2": [0.51, 0.21, 0.81, 0.31, 0.15, 0.41, 0.11, 0.21],
    "D3": [0.19, 0.74, 0.24, 0.91, 0.81, 0.42, 0.43, 0.43],
    "D4": [0.24, 0.82, 0.16, 0.85, 0.94, 0.53, 0.38, 0.55],
    "D5": [0.40, 0.73, 0.53, 0.25, 0.83, 0.85, 0.55, 0.12],
    "D6": [0.92, 0.24, 0.57, 0.57, 0.53, 0.97, 0.83, 0.77]
}

vectors = list(data.keys())
results = []

print("--- 모든 쌍에 대한 유사도 분석 결과 ---")
for i in range(len(vectors)):
    for j in range(i + 1, len(vectors)):
        v1_name, v2_name = vectors[i], vectors[j]
        c_sim = cosine_sim(data[v1_name], data[v2_name])
        p_corr = pearson_corr(data[v1_name], data[v2_name])
        results.append((v1_name, v2_name, c_sim, p_corr))
        print(f"[{v1_name}-{v2_name}] 코사인: {c_sim:.4f} | 피어슨: {p_corr:.4f}")

best_cos = max(results, key=lambda x: x[2])
best_pea = max(results, key=lambda x: x[3])

print(f"\n코사인 유사도 기준 가장 가까운 쌍: {best_cos[0]}-{best_cos[1]} ({best_cos[2]:.4f})")
print(f"피어슨 상관 계수 기준 가장 가까운 쌍: {best_pea[0]}-{best_pea[1]} ({best_pea[3]:.4f})")



