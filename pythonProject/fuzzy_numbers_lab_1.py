# coding=utf-8
import matplotlib.pyplot as plt
import collections

# old ticket number is tv-6149


# V = 9 + 4 + 1 = 14

# A = {x, m(x)}, where x = f(i) and i =  [0, 14]
# A = {<i, i / 14 >}, for i = [0, 14]
# B = {<0.5 * i , i / 28>} for i = [0, 14]

# compute the A and B for all i

fuzzy_A_X = []
fuzzy_A_M = []

for i in range(0, 15):
    i = i * 1.0
    fuzzy_A_X.append(i)
    fuzzy_A_M.append(i / 14)

fuzzy_B_X = []
fuzzy_B_M = []
for i in range(0, 15):
    i = i * 1.0
    fuzzy_B_X.append(0.5 * i)
    fuzzy_B_M.append(i / 28)

figure, axis = plt.subplots(1, 2)
axis[0].plot(fuzzy_A_X, fuzzy_A_M, marker='*')
axis[0].set_title("A")
axis[1].plot(fuzzy_B_X, fuzzy_B_M, marker='*')
axis[1].set_title("B")
plt.show()

# compute  висоту, моду, носія, ядро, множину α-рівня
# висота
print max(fuzzy_A_M)
print max(fuzzy_B_M)

# мода
print fuzzy_A_X[fuzzy_A_M.index(max(fuzzy_A_M))]
print fuzzy_B_X[fuzzy_B_M.index(max(fuzzy_B_M))]

# носій
fuzzy_A_Bearer = []
fuzzy_B_Bearer = []
for i in range(0, 15):
    if fuzzy_A_M[i] > 0:
        fuzzy_A_Bearer.append(fuzzy_A_X[i])
    if fuzzy_B_M[i] > 0:
        fuzzy_B_Bearer.append(fuzzy_B_X[i])

print fuzzy_A_Bearer
print fuzzy_B_Bearer

# ядро
fuzzy_A_Core = []
fuzzy_B_Core = []
for i in range(0, 15):
    if fuzzy_A_M[i] == 1:
        fuzzy_A_Core.append(fuzzy_A_X[i])
    if fuzzy_B_M[i] == 1:
        fuzzy_B_Core.append(fuzzy_B_X[i])

print fuzzy_A_Core
print fuzzy_B_Core

# множина α-рівня
a_l = 0.7
fuzzy_A_a = []
fuzzy_B_a = []
for i in range(0, 15):
    if fuzzy_A_M[i] >= a_l:
        fuzzy_A_a.append(fuzzy_A_X[i])
    if fuzzy_B_M[i] >= a_l:
        fuzzy_B_a.append(fuzzy_B_X[i])

print fuzzy_A_a
print fuzzy_B_a

# T = 14 % 3 = 2    обмежена
# операції об’єднання, перетину
x = list(set(fuzzy_A_X + fuzzy_B_X))
x.sort()

fuzzy_union = []
fuzzy_intersection = []
for i in range(len(x)):
    m_a = x[i] * 1.0 / 14
    m_b = x[i] * 1.0 / 14
    fuzzy_union.append(min(1.0, m_a + m_b))
    fuzzy_intersection.append(max(0.0, m_a + m_b - 1))

print fuzzy_union

figure, axis = plt.subplots(1, 2)
axis[0].plot(x, fuzzy_union, marker='*')
axis[0].set_title("Union")
axis[1].plot(x, fuzzy_intersection, marker='*')
axis[1].set_title("Intersection")
plt.show()

# доповнення кінцевих нечітких множин
fuzzy_A_D = []
fuzzy_B_D = []
for i in range(0, 15):
    fuzzy_A_D.append(1.0 - fuzzy_A_M[i])
    fuzzy_B_D.append(1.0 - fuzzy_B_M[i])

figure, axis = plt.subplots(1, 2)
axis[0].plot(fuzzy_A_X, fuzzy_A_D, marker='*')
axis[0].set_title("A")
axis[1].plot(fuzzy_B_X, fuzzy_B_D, marker='*')
axis[1].set_title("B")
plt.show()

# операції додавання, віднімання, множення та ділення над нечіткими числами
# addition
A_B_addition = {}
for i in range(15):
    z = fuzzy_A_X[i] + fuzzy_B_X[i]
    z_m = min(fuzzy_A_M[i], fuzzy_B_M[i])
    A_B_addition[z] = max(A_B_addition.get(z, 0.0), z_m)

A_B_addition = collections.OrderedDict(sorted(A_B_addition.items()))

figure, axis = plt.subplots(2, 2)

axis[0, 0].plot(A_B_addition.keys(), A_B_addition.values(), marker='*')
axis[0, 0].set_title("addition")

# subtraction
A_B_subtraction = {}
for i in range(15):
    z = fuzzy_A_X[i] - fuzzy_B_X[i]
    z_m = min(fuzzy_A_M[i], fuzzy_B_M[i])
    A_B_subtraction[z] = max(A_B_subtraction.get(z, 0.0), z_m)

A_B_subtraction = collections.OrderedDict(sorted(A_B_subtraction.items()))
axis[0, 1].plot(A_B_subtraction.keys(), A_B_subtraction.values(), marker='*')
axis[0, 1].set_title("subtraction")

# multiplication
A_B_multiplication = {}
for i in range(15):
    z = fuzzy_A_X[i] * fuzzy_B_X[i]
    z_m = min(fuzzy_A_M[i], fuzzy_B_M[i])
    A_B_multiplication[z] = max(A_B_multiplication.get(z, 0.0), z_m)

A_B_multiplication = collections.OrderedDict(sorted(A_B_multiplication.items()))

axis[1, 0].plot(A_B_multiplication.keys(), A_B_multiplication.values(), marker='*')
axis[1, 0].set_title("multiplication")

# division
A_B_division = {}
for i in range(15):
    if fuzzy_B_X[i] == 0:
        continue
    z = fuzzy_A_X[i] / fuzzy_B_X[i]
    z_m = min(fuzzy_A_M[i], fuzzy_B_M[i])
    A_B_division[z] = max(A_B_division.get(z, 0.0), z_m)

A_B_division = collections.OrderedDict(sorted(A_B_division.items()))

axis[1, 1].plot(A_B_division.keys(), A_B_division.values(), marker='*')
axis[1, 1].set_title("division")
plt.show()
