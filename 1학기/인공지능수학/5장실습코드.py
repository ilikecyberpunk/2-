#1번
def EuclidDist(a, b):
    sum = 0
    for i in range(len(a)):
        sum += (a[i] - b[i])**2
    sum = sum*(1/2)
    print(sum)
    return sum


def Manhattan(a, b):
    sum = 0
    for i in range(len(a)):
        sum += abs(a[i] - b[i])
    print(sum)
    return sum

# 2번
D = [[0.32,0.57,0.65,0.53,0.57,0.15,0.81,0.75],#D1
    [0.54,0.12,0.98,0.13,0.51,0.14,0.88,0.12],#D2
    [0.05,0.47,0.31,0.49,0.19,0.24,0.04,0.34],#D3
    [0.38,0.58,0.61,0.58,0.49,0.25,0.83,0.77],#D4
    [0.51,0.27,0.75,0.33,0.38,0.48,0.55,0.64],#D5
    [0.28,0.32,0.45,0.75,0.35,0.98,0.38,0.88]]#D6

EuclidDist(D[0], D[1])
Manhattan(D[1], D[2])

print()
# 3번
Dn = [0.48,0.61,0.37,0.64,0.21,0.21,0.17,0.75]
for i in range(len(D)):
    EuclidDist(D[i], Dn)
#D3과 가장 유사합니다




        


