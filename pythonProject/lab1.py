import numpy as np
import matplotlib.pyplot as plt


def write_to_file(data, file_name):
    with open(file_name, 'wb') as f:
        f.write(data.tobytes())
        f.close()


def read_from_file(file_name):
    with open(file_name, 'rb') as f:
        data = f.read()
        f.close()
    arr = np.frombuffer(data, dtype=np.int16)
    return arr


def filter_frequency(data, a):
    filtered_data = np.zeros_like(data)
    filtered_data[0] = data[0] / 2
    for i in range(1, len(data)):
        filtered_data[i] = (1 - a) * data[i] + a * filtered_data[i - 1]
    return filtered_data


def show(name, x, y_original, y_filtered):
    global fig, ax
    fig, ax = plt.subplots(figsize=(20, 10))
    # ax.plot(x, generated_original_data, label='original')
    ax.plot(x, y_original, label='original')
    # ax.plot(x, generated_filtered_data, label='filtered')
    ax.plot(x, y_filtered, label='filtered')
    ax.set_title(name)
    # ax.set_title()
    ax.set_xlabel('Time')
    ax.set_ylabel('Amplitude')
    ax.legend()
    plt.grid(True)
    plt.show()


x = [i * 1.0 / 1000 for i in np.arange(1000)]

original_data = read_from_file("001.bk0")
filtered_data = filter_frequency(original_data, 0.9)
write_to_file(filtered_data, "name.da1")

show("001.bk0 data", x, original_data, filtered_data)


def make_wave(x, frequency, amplitude):
    return [amplitude * np.sin(2 * np.pi * frequency * i) for i in x]


wave1 = make_wave(x, 50, 220)
wave2 = make_wave(x, 60, 110)
wave3 = make_wave(x, 400, 36)
generated_original_data = [wave1[i] + wave2[i] + wave3[i] for i in range(len(x))]
generated_filtered_data = filter_frequency(generated_original_data, 0.8)

show("generated data", x, generated_original_data, generated_filtered_data)
