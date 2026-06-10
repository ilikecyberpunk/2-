import matplotlib.pyplot as plt
import numpy as np

def fdiff(f):
    def diff(x):
        h = 1e-8
        return (f(x + h) - f(x - h)) / (2 * h)
    return diff

def f(x):
    return 3 * (x - 5) ** 2 + 2

def is_converged(x_prev, x, threshold):
    if abs(x_prev - x) < threshold:
        return True
    else:
        return False

def gradient_descent(f):
    lr = 0.01
    x = 100
    threshold = 1e-8
    t = 1

    history_x = [x]
    history_f = [f(x)]

    while True:
        x_prev = x

        diff = fdiff(f)
        g = diff(x)

        x = x - lr * g
        t += 1

        history_x.append(x)
        history_f.append(f(x))

        print(x)

        if is_converged(x_prev, x, threshold):
            break

    return x, history_x, history_f

result_x, history_x, history_f = gradient_descent(f)

print("최적해:", result_x)
print("최솟값:", f(result_x))

x_range = np.linspace(-5, 110, 500)
y_range = 3 * (x_range - 5) ** 2 + 2

fig, axes = plt.subplots(1, 2, figsize=(14, 5))

ax1 = axes[0]
ax1.plot(x_range, y_range, 'b-', linewidth=2, label='f(x) = 3(x-5)^2 + 2')
n_show = min(30, len(history_x))
ax1.scatter(history_x[:n_show], history_f[:n_show],
            c=range(n_show), cmap='Reds', s=50, zorder=5, label='Search Path')
ax1.scatter([result_x], [f(result_x)], color='red', s=200, zorder=6,
            marker='*', label=f'Optimal x={result_x:.4f}')
ax1.set_xlabel('x')
ax1.set_ylabel('f(x)')
ax1.set_title('Gradient Descent - Objective Function')
ax1.legend()
ax1.grid(True, alpha=0.3)
ax1.set_xlim(-5, 110)

ax2 = axes[1]
ax2.plot(range(len(history_f)), history_f, 'r-o', markersize=3, linewidth=1.5)
ax2.axhline(y=2, color='blue', linestyle='--', linewidth=1.5, label='Min f(x)=2')
ax2.set_xlabel('Iteration (t)')
ax2.set_ylabel('f(x)')
ax2.set_title('Convergence of f(x)')
ax2.legend()
ax2.grid(True, alpha=0.3)
ax2.set_yscale('log')

plt.tight_layout()
plt.show()