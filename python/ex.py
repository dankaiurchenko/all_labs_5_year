import pyperclip
pyperclip.copy('fron the python motherfucker!!!')


a = 20;


def b():
	global a
	a = 3;
	print(a)


print(a)

b()

print(a)