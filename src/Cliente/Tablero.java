package Cliente;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Tablero extends JFrame implements ActionListener {
	final int n = 4;
	JButton[][] boton;
	Font f;
	boolean activo;
	Posicion p;

	public Tablero(final Posicion pp) {
		super("4 en raya");
		this.p = pp;
		this.setSize(600, 600);
		this.setResizable(false);
		this.activo = false;
		this.f = new Font("Monospaced", 0, 80);
		this.boton = new JButton[n][n];
		this.setLayout(new GridLayout(n, n));
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				(this.boton[i][j] = new JButton()).setActionCommand(String.valueOf(i) + "-" + j);
				this.boton[i][j].addActionListener(this);
				this.boton[i][j].setFont(this.f);
				this.add(this.boton[i][j]);
			}
		}
		this.repaint();
		this.setVisible(true);
	}

	public void Poner(final int i, final int j, final char letra) {
		this.boton[i][j].setText(new StringBuilder(String.valueOf(letra)).toString());
		this.boton[i][j].setEnabled(false);
		this.repaint();
	}

	public void Poner(final JButton j, final char letra) {
		j.setText(new StringBuilder(String.valueOf(letra)).toString());
		j.setEnabled(false);
		j.repaint();
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (this.activo) {
			final String[] aux = e.getActionCommand().split("-");
			final int fila = Integer.parseInt(aux[0]);
			final int columna = Integer.parseInt(aux[1]);
			this.Poner((JButton) e.getSource(), this.p.letra());
			this.p.cargaPosicion(fila, columna);
			this.activo = false;
			this.p.despierto();
		}
	}

	public void Activo() {
		this.setTitle("Es tu turno");
		this.activo = true;
	}

	public void Desactivo() {
		this.setTitle("Espera a que el otro juegue");
		this.activo = false;
	}

	public void gano() {
		this.setTitle(" ¡4 EN RAYA! ");
	}

	public boolean hueco() {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (this.boton[i][j].getText().equals("")) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean linea(final int x0, final int y0, final int x1, final int y1, final int x2, final int y2,
			final int x3, final int y3) {
		return !this.boton[x0][y0].getText().equals("")
				&& this.boton[x0][y0].getText().equals(this.boton[x1][y1].getText())
				&& this.boton[x1][y1].getText().equals(this.boton[x2][y2].getText())
				&& this.boton[x2][y2].getText().equals(this.boton[x3][y3].getText());
	}

	public boolean enraya() {
		// horizontal
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n - 3; col++) {
				if (boton[row][col].getText().equals("X") && boton[row][col + 1].getText().equals("X")
						&& boton[row][col + 2].getText().equals("X") && boton[row][col + 3].getText().equals("X")) {
					return true;
				}
				if (boton[row][col].getText().equals("O") && boton[row][col + 1].getText().equals("O")
						&& boton[row][col + 2].getText().equals("O") && boton[row][col + 3].getText().equals("O")) {
					return true;
				}
			}
		}

		// vertical
		for (int col = 0; col < n; col++) {
			for (int row = 0; row < n - 3; row++) {
				if (boton[row][col].getText().equals("X") && boton[row + 1][col].getText().equals("X")
						&& boton[row + 2][col].getText().equals("X") && boton[row + 3][col].getText().equals("X")) {
					return true;
				}
				if (boton[row][col].getText().equals("O") && boton[row + 1][col].getText().equals("O")
						&& boton[row + 2][col].getText().equals("O") && boton[row + 3][col].getText().equals("O")) {
					return true;
				}
			}
		}

		// diagonal hacia arriba
		for (int row = 3; row < n; row++) {
			for (int col = 0; col < n - 3; col++) {
				if (boton[row][col].getText().equals("X") && boton[row - 1][col + 1].getText().equals("X")
						&& boton[row - 2][col + 2].getText().equals("X")
						&& boton[row - 3][col + 3].getText().equals("X")) {
					return true;
				}
				if (boton[row][col].getText().equals("O") && boton[row - 1][col + 1].getText().equals("O")
						&& boton[row - 2][col + 2].getText().equals("O")
						&& boton[row - 3][col + 3].getText().equals("O")) {
					return true;
				}
			}
		}

		// diagonal hacia abajo
		for (int row = 0; row < n - 3; row++) {
			for (int col = 0; col < n - 3; col++) {
				if (boton[row][col].getText().equals("X") && boton[row + 1][col + 1].getText().equals("X")
						&& boton[row + 2][col + 2].getText().equals("X")
						&& boton[row + 3][col + 3].getText().equals("X")) {
					return true;
				}
				if (boton[row][col].getText().equals("O") && boton[row + 1][col + 1].getText().equals("O")
						&& boton[row + 2][col + 2].getText().equals("O")
						&& boton[row + 3][col + 3].getText().equals("O")) {
					return true;
				}
			}
		}

		return false;

	}
}
