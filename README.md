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
   [```bash `sh compile.sh` ``` Caso queira rodar de novo, basta ```bash `sh run.sh` ```


3. **Estrutura de Pastas:**  
+ assets  
+ bin
+ src
3. **Versão do JAVA utilizada:**  
   `java 24.0.1`

---

## Vídeo de Demonstração

- [A fazer]

---

## Prints da Execução
>[!NOTE]
>O programa tenta carregar a GUI com gtk, então o a sua GUI certamente será diferente da minha
1. Menu Principal:  
   ![Inserir Print 1](../assets/add_aluno_dialogo.png)

2. Cadastro de Aluno:  
   ![Inserir Print 2](../assets/add_aluno.png)

3. Relatório de Frequência/Notas:  
   ![Inserir Print 3](caminho/do/print3.png)

---

## Principais Funcionalidades Implementadas

- [x] Cadastro, listagem, matrícula e trancamento de alunos (Normais e Especiais) 
- [x] Cadastro de disciplinas e criação de turmas (presenciais e remotas(a fazer) )
- [x] Matrícula de alunos em turmas, respeitando vagas e pré-requisitos
- [x] Lançamento de notas e controle de presença
- [x] Cálculo de média final e verificação de aprovação/reprovação
- [ ] Relatórios de desempenho acadêmico por aluno, turma e disciplina
- [x] Persistência de dados em arquivos (.txt ou .csv)
- [x] Tratamento de duplicidade de matrículas
- [x] Uso de herança, polimorfismo e encapsulamento

---

## Observações (Extras ou Dificuldades)

- [interface grafica,o java swing em geral foi ruim de escrever e lidar com]
- [o programa assume que o sistema vai ser usado especificamente pela FCTE, então os cursos pré-instânciados são: Eletrônica, software e Aeroespacial]
- [A complexidade da Csv cresce muito rapido, a logica por traz de adicionar notas ficou extremamente convoluta]
-  []
---

## Contato

-
