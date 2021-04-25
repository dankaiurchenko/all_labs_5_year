# coding=utf-8
import matplotlib.pyplot as plt
import collections

# old ticket number is tv-6149


# V = 9 + 4 + 1 = 14

# A = {x, m(x)}, where x = f(i) and i =  [0, 14]
# A = {<i, i / 14 >}, for i = [0, 14]
# B = {<0.5 * i , i / 28>} for i = [0, 14]

# compute the A and B for all i

fuzzy_A = {}

for i in range(0, 15):
    fuzzy_A[1.0 * i] = i * 1.0 / 14

fuzzy_B = {}
for i in range(0, 15):
    fuzzy_B[0.5 * i] = i * 1.0 / 28

figure, axis = plt.subplots(1, 2)
axis[0].plot(fuzzy_A.keys(), fuzzy_A.values(), marker='*')
axis[0].set_title("A")
axis[1].plot(fuzzy_B.keys(), fuzzy_B.values(), marker='*')
axis[1].set_title("B")
plt.show()

# compute  висоту, моду, носія, ядро, множину α-рівня
# висота
print max(fuzzy_A.values())
print max(fuzzy_B.values())

# мода
print fuzzy_A.keys()[fuzzy_A.values().index(max(fuzzy_A.values()))]
print fuzzy_B.keys()[fuzzy_B.values().index(max(fuzzy_B.values()))]

# носій
fuzzy_A_Bearer = []
fuzzy_B_Bearer = []
for key, value in fuzzy_A.items():
    if value > 0:
        fuzzy_A_Bearer.append(key)

for key, value in fuzzy_B.items():
    if value > 0:
        fuzzy_B_Bearer.append(key)

print fuzzy_A_Bearer
print fuzzy_B_Bearer

# ядро
fuzzy_A_Core = []
fuzzy_B_Core = []
for key, value in fuzzy_A.items():
    if value == 1:
        fuzzy_A_Core.append(key)

for key, value in fuzzy_B.items():
    if value == 1:
        fuzzy_B_Core.append(key)

print fuzzy_A_Core
print fuzzy_B_Core

# множина α-рівня
a_l = 0.7
fuzzy_A_a = []
fuzzy_B_a = []

for key, value in fuzzy_A.items():
    if value >= a_l:
        fuzzy_A_a.append(key)

for key, value in fuzzy_B.items():
    if value >= a_l:
        fuzzy_B_a.append(key)

print fuzzy_A_a
print fuzzy_B_a

# T = 14 % 3 = 2    обмежена
# операції об’єднання, перетину
x = list(set(fuzzy_A.keys() + fuzzy_B.keys()))
x.sort()
print x

fuzzy_union = {}
fuzzy_intersection = {}
for i in x:
    print i
    m_a = i * 1.0 / 14
    m_b = i * 1.0 / 14
    fuzzy_union[i] = min(1.0, m_a + m_b)
    fuzzy_intersection[i] = max(0.0, m_a + m_b - 1)

fuzzy_union = collections.OrderedDict(sorted(fuzzy_union.items()))
fuzzy_intersection = collections.OrderedDict(sorted(fuzzy_intersection.items()))

print fuzzy_union

figure, axis = plt.subplots(1, 2)
axis[0].plot(fuzzy_union.keys(), fuzzy_union.values(), marker='*')
axis[0].set_title("Union")
axis[1].plot(fuzzy_intersection.keys(), fuzzy_intersection.values(), marker='*')
axis[1].set_title("Intersection")
plt.show()

# доповнення кінцевих нечітких множин
fuzzy_A_D = {}
fuzzy_B_D = {}

for key, value in fuzzy_A.items():
    fuzzy_A_D[key] = 1.0 - value

for key, value in fuzzy_B.items():
    fuzzy_B_D[key] = 1.0 - value

figure, axis = plt.subplots(1, 2)
axis[0].plot(fuzzy_A_D.keys(), fuzzy_A_D.values(), marker='*')
axis[0].set_title("A")
axis[1].plot(fuzzy_B_D.keys(), fuzzy_B_D.values(), marker='*')
axis[1].set_title("B")
plt.show()

# операції додавання, віднімання, множення та ділення над нечіткими числами
# http://moodle.ipo.kpi.ua/moodle/mod/resource/view.php?id=38981

# addition
A_B_addition = {}
for a_key, a_value in fuzzy_A.items():
    for b_key, b_value in fuzzy_B.items():
        z = a_key + b_key
        z_m = min(a_value, b_value)
        A_B_addition[z] = max(A_B_addition.get(z, 0.0), z_m)

A_B_addition = collections.OrderedDict(sorted(A_B_addition.items()))

figure, axis = plt.subplots(2, 2)

axis[0, 0].plot(A_B_addition.keys(), A_B_addition.values(), marker='*')
axis[0, 0].set_title("addition")

# subtraction
A_B_subtraction = {}
for a_key, a_value in fuzzy_A.items():
    for b_key, b_value in fuzzy_B.items():
        z = a_key - b_key
        z_m = min(a_value, b_value)
        A_B_subtraction[z] = max(A_B_subtraction.get(z, 0.0), z_m)

A_B_subtraction = collections.OrderedDict(sorted(A_B_subtraction.items()))
axis[0, 1].plot(A_B_subtraction.keys(), A_B_subtraction.values(), marker='*')
axis[0, 1].set_title("subtraction")

# multiplication
A_B_multiplication = {}
for a_key, a_value in fuzzy_A.items():
    for b_key, b_value in fuzzy_B.items():
        z = a_key * b_key
        z_m = min(a_value, b_value)
        A_B_multiplication[z] = max(A_B_multiplication.get(z, 0.0), z_m)

A_B_multiplication = collections.OrderedDict(sorted(A_B_multiplication.items()))

axis[1, 0].plot(A_B_multiplication.keys(), A_B_multiplication.values(), marker='*')
axis[1, 0].set_title("multiplication")

# division
A_B_division = {}
for a_key, a_value in fuzzy_A.items():
    for b_key, b_value in fuzzy_B.items():
        if b_key == 0:
            continue
        z = a_key / b_key
        z_m = min(a_value, b_value)
        A_B_division[z] = max(A_B_division.get(z, 0.0), z_m)

A_B_division = collections.OrderedDict(sorted(A_B_division.items()))

axis[1, 1].plot(A_B_division.keys(), A_B_division.values(), marker='*')
axis[1, 1].set_title("division")
plt.show()
