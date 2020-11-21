# coding: utf-8
import speech_recognition as sr

r = sr.Recognizer()
with sr.Microphone() as source:
    audio = r.listen(source, 2, 4)

try:
    print(r.recognize_google(audio))
except sr.UnknownValueError:
    print("sorry. could not understand")
except sr.RequestError as e:
    print("Error; {0}".format(e))
