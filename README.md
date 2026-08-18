# oBurocrata
Software Development work assignment.

[PT] Este repositório conta com um `Makefile` configurado para automatizar a compilação, execução, geração de documentação e limpeza dos arquivos do projeto.

[EN] This repository includes a `Makefile` configured to automate build, execution, documentation generation, and file cleanup for the Java project.

---

## 🛠️ Prerequisites / Pré-requisitos

[PT] Certifique-se de ter o **JDK** e o **Make** instalados no seu sistema:  
[EN] Make sure you have the **JDK** and **Make** installed on your system:

* **Java JDK** (`javac`, `java`, `javadoc`)
* **GNU Make**

---

## 📁 Directory Structure / Estrutura de Diretórios

```
.
├── src/        # [PT] Código-fonte Java / [EN] Java source files
├── out/        # [PT] Arquivos .class compilados / [EN] Compiled .class files (Auto-generated)
├── javadoc/    # [PT] Documentação HTML / [EN] Javadoc HTML output (Auto-generated)
└── Makefile
```

---

## 🚀 Usage / Como Usar

[PT] Execute os comandos abaixo diretamente no terminal na raiz do projeto:  
[EN] Run the following commands in your terminal at the root of the project:

| Command / Comando | Description (PT) | Description (EN) |
| :--- | :--- | :--- |
| `make` / `make build` | Compila todos os arquivos `.java` do diretório `src/` para a pasta `out/`. | Compiles all `.java` files inside `src/` to the `out/` directory. |
| `make run` | Compila o projeto e executa a classe principal `Main`. | Compiles the project and runs the `Main` class. |
| `make javadoc` | Gera a documentação do código usando o `javadoc` na pasta `javadoc/`. | Generates HTML documentation using `javadoc` inside `javadoc/`. |
| `make docs` | Gera o Javadoc e abre automaticamente o `index.html` no navegador. | Generates Javadoc and automatically opens `index.html` in your default browser. |
| `make clean` | Remove a pasta `out/` com os arquivos compilados. | Removes the `out/` directory containing compiled files. |

---

> **📌 Note / Nota:**  
> **[PT]** O comando `make run` assume que a classe com o método `main` é a `Main` no pacote raiz.  
> **[EN]** The `make run` command assumes that your entry point class is named `Main` and located in the root package.