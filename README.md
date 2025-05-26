# Sistema Acadêmico - FCTE

## Descrição do Projeto

Desenvolvimento de um sistema acadêmico para gerenciar alunos, disciplinas, professores, turmas, avaliações e frequência, utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

O enunciado do trabalho pode ser encontrado aqui:
- [Trabalho 1 - Sistema Acadêmico](https://github.com/lboaventura25/OO-T06_2025.1_UnB_FCTE/blob/main/trabalhos/ep1/README.md)

## Dados do Aluno

- **Nome completo:** [L.M.S]
- **Matrícula:** [242005140]
- **Curso:** [Eletrônica/software]
- **Turma:** [6]

---

## Instruções para Compilação e Execução

1. **Compilação e execução**  
   ```bash `sh compile.sh` ``` Caso queira rodar de novo, basta ```bash `sh run.sh` ```


3. **Estrutura de Pastas:**  
+ assets  
+ bin
+ src
3. **Versão do JAVA utilizada:**  
   `java 24.0.1`

---

## Vídeo de Demonstração
>[!NOTE]
>Interface grafica varia a depender do sist operacional e tema do GTK



![video](https://github.com/user-attachments/assets/d661ab2a-865a-4702-a7e7-6ab39073dfa4)
---

## Prints da Execução
>[!NOTE]
>O programa tenta carregar a GUI com gtk, então o a sua GUI certamente será diferente da minha
1. Menu Principal:  
   ![Inserir Print 1](/assets/menu_principal.png)

2. Cadastro de Aluno:  
   ![Inserir Print 2](/assets/add_aluno_dialogo.png)

3. Relatório de Frequência/Notas:  
   ![Inserir Print 3](caminho/do/print3.png)

---

## Principais Funcionalidades Implementadas

- [x] Cadastro, listagem, matrícula e trancamento de alunos (Normais e Especiais) 
- [x] Cadastro de disciplinas e criação de turmas (presenciais e remotas(não fiz))
- [x] Matrícula de alunos em turmas, respeitando vagas e pré-requisitos (faltou pre-requisitos)
- [x] Lançamento de notas e controle  de presença(Não fiz graficamente) 
- [x] Cálculo de média final e verificação de aprovação/reprovação
- [x] Relatórios de desempenho acadêmico por turma e disciplina (Não tive tempo de fazer por aluno )
- [x] Persistência de dados em arquivos (.txt ou .csv)
- [x] Tratamento de duplicidade de matrículas
- [x] Uso de herança, polimorfismo e encapsulamento

---

## Observações (Extras ou Dificuldades)

- interface grafica,o java swing em geral foi ruim de escrever e se lidar com
- Utilizar somente java 24.0.1, sem nenhuma biblioteca externa por que eu não queria usar grade, maven, ou qualquer dependencia externa
- A complexidade da Csv cresce muito rapido, a logica por traz de adicionar notas ficou extremamente convoluta.
---

## Contato

-
