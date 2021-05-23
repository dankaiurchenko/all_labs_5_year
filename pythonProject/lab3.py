import numpy as np
import matplotlib.pyplot as plt


def read_from_file(file_name):
    with open(file_name, 'rb') as f:
        data = f.read()
        f.close()
    arr = np.frombuffer(data, dtype=np.int16)
    return arr


def show(title, x, y, x_label, y_label):
    fig, ax = plt.subplots()
    ax.plot(x, y)
    ax.set_title(title)
    ax.set_xlabel(x_label)
    ax.set_ylabel(y_label)
    ax.legend()
    plt.grid(True)
    plt.show()


def generate(x, frequency, amplitude):
    return [amplitude * np.sin(2 * np.pi * frequency * i) for i in x]


def generate_data(generated_x):
    w1 = generate(generated_x, 50, 220)
    w2 = generate(generated_x, 60, 110)
    w3 = generate(generated_x, 400, 36)
    return [w1[i] + w2[i] + w3[i] for i in range(len(generated_x))]


def process_data(title, x, y, N):
    show(title + "original", x, y, "Time", "Amplitude")
    # Raw FFT
    # Compute the Discrete Fourier Transform sample frequencies
    frequencies = np.fft.fftfreq(N, d=0.001)
    mask = frequencies > 0
    # Compute the one-dimensional discrete Fourier Transform
    fft_values = np.fft.fft(y)
    show(title + "Raw FFT", frequencies, np.abs(fft_values), "Frequency", "Values")
    # True FFT
    fft_theo = 2.0 * np.abs(fft_values / N)
    show(title + "True FFT", frequencies[mask], fft_theo[mask], "Frequency", "Amplitude")
    # IFFT
    # Compute the one-dimensional inverse discrete Fourier Transform
    ifft_values = np.fft.ifft(fft_values)
    show(title + "IFFT", x, ifft_values.real, "Time", "Amplitude")


N = 1000
generated_x = [i * 1.0 / 1000 for i in np.arange(N)]
generated_y = generate_data(generated_x)
process_data("generated  ", generated_x, generated_y, N)

file_y = read_from_file("001.bk0")
process_data("from 001.bk0  ", generated_x, file_y, N)
