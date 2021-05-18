# # coding=utf-8
# import matplotlib.pyplot as plt
# import collections
#
# # old ticket number is tv-6149
# # V = 9 + 4 + 1 = 14
#
# # A = {<i, i / 14 >}, for i = [0, 14]
# # B = {<0.5 * i , i / 28>} for i = [0, 14]
#
# # compute the A and B for all i
#
# fuzzy_A = {}
#
# for i in range(0, 15):
#     fuzzy_A[1.0 * i] = i * 1.0 / 14
#
# fuzzy_B = {}
# for i in range(0, 15):
#     fuzzy_B[0.5 * i] = i * 1.0 / 28
#
# figure, axis = plt.subplots(1, 2)
# axis[0].plot(fuzzy_A.keys(), fuzzy_A.values(), marker='*')
# axis[0].set_title("A")
# axis[1].plot(fuzzy_B.keys(), fuzzy_B.values(), marker='*')
# axis[1].set_title("B")
# plt.show()
#
# # обрахувати висоту, моду, носія, ядро, множину α-рівня
# # висота
# print max(fuzzy_A.values())
# print max(fuzzy_B.values())
#
# # мода
# print fuzzy_A.keys()[fuzzy_A.values().index(max(fuzzy_A.values()))]
# print fuzzy_B.keys()[fuzzy_B.values().index(max(fuzzy_B.values()))]
#
#
# # носій
# def get_bearer(values):
#     bearer = []
#     for key, value in values.items():
#         if value > 0:
#             bearer.append(key)
#     return bearer
#
#
# print get_bearer(fuzzy_A)
# print get_bearer(fuzzy_B)
#
#
# # ядро
# def get_core(values):
#     core = []
#     for key, value in values.items():
#         if value == 1:
#             core.append(key)
#     return core
#
#
# print get_core(fuzzy_A)
# print get_core(fuzzy_B)
#
#
# # множина α-рівня
# def get_a_level(values):
#     a_l = 0.7
#     a_level = []
#     for key, value in values.items():
#         if value >= a_l:
#             a_level.append(key)
#     return a_level
#
#
# print get_a_level(fuzzy_A)
# print get_a_level(fuzzy_B)
#
#
# # T = 14 % 3 = 2    обмежена
# # операції об’єднання, перетину
# x = list(set(fuzzy_A.keys() + fuzzy_B.keys()))
# x.sort()
# print x
#
# fuzzy_union = {}
# fuzzy_intersection = {}
# for i in x:
#     print i
#     m_a = i * 1.0 / 14
#     m_b = i * 1.0 / 14
#     fuzzy_union[i] = min(1.0, m_a + m_b)
#     fuzzy_intersection[i] = max(0.0, m_a + m_b - 1)
#
# fuzzy_union = collections.OrderedDict(sorted(fuzzy_union.items()))
# fuzzy_intersection = collections.OrderedDict(sorted(fuzzy_intersection.items()))
#
# figure, axis = plt.subplots(1, 2)
# axis[0].plot(fuzzy_union.keys(), fuzzy_union.values(), marker='*')
# axis[0].set_title("Union")
# axis[1].plot(fuzzy_intersection.keys(), fuzzy_intersection.values(), marker='*')
# axis[1].set_title("Intersection")
# plt.show()
#
# # доповнення кінцевих нечітких множин
# fuzzy_A_D = {}
# fuzzy_B_D = {}
#
# for key, value in fuzzy_A.items():
#     fuzzy_A_D[key] = 1.0 - value
#
# for key, value in fuzzy_B.items():
#     fuzzy_B_D[key] = 1.0 - value
#
# figure, axis = plt.subplots(1, 2)
# axis[0].plot(fuzzy_A_D.keys(), fuzzy_A_D.values(), marker='*')
# axis[0].set_title("Complement A")
# axis[1].plot(fuzzy_B_D.keys(), fuzzy_B_D.values(), marker='*')
# axis[1].set_title("Complement B")
# plt.show()
#
#
# # операції додавання, віднімання, множення та ділення над нечіткими числами
# # http://moodle.ipo.kpi.ua/moodle/mod/resource/view.php?id=38981
#
#
# def perform_arithmetical_operation(a, b, c):
#     a_b_result = {}
#     for a_key, a_value in a.items():
#         for b_key, b_value in b.items():
#             z = c(a_key, b_key)
#             z_m = min(a_value, b_value)
#             a_b_result[z] = max(a_b_result.get(z, 0.0), z_m)
#     return collections.OrderedDict(sorted(a_b_result.items()))
#
#
# figure, axis = plt.subplots(2, 2)
#
# # addition
# A_B_addition = perform_arithmetical_operation(fuzzy_A, fuzzy_B, (lambda a, b: a + b))
# axis[0, 0].plot(A_B_addition.keys(), A_B_addition.values(), marker='*')
# axis[0, 0].set_title("addition")
#
# # subtraction
# A_B_subtraction = perform_arithmetical_operation(fuzzy_A, fuzzy_B, (lambda a, b: a - b))
# axis[0, 1].plot(A_B_subtraction.keys(), A_B_subtraction.values(), marker='*')
# axis[0, 1].set_title("subtraction")
#
# # multiplication
# A_B_multiplication = perform_arithmetical_operation(fuzzy_A, fuzzy_B, (lambda a, b: a * b))
# axis[1, 0].plot(A_B_multiplication.keys(), A_B_multiplication.values(), marker='*')
# axis[1, 0].set_title("multiplication")
#
# # division
# A_B_division = perform_arithmetical_operation(fuzzy_A, fuzzy_B, (lambda a, b: a / b if b != 0 else 0))
# axis[1, 1].plot(A_B_division.keys(), A_B_division.values(), marker='*')
# axis[1, 1].set_title("division")
#
# plt.show()
#
#
#
#
#


print (20 + 30 + 100 + 1000 + 3000 + 5000)
print (1120 + 1690 + 1790 + 2440 + 3540 + 4000)
print (-2220 + -2350 + -2480 + 5120 + 22920 + 42000)
