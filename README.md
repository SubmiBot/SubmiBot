# SubmiBot

O SubmiBot é um plug-in de submissão de tarefas do Eclipse IDE para o Canvas (Ferramenta de Sala de Aula) baseado na automação do processo de zipagem e envio de um projeto.

A ferramenta funciona como qualquer outro plug-in do Eclipse e torna fácil **localizar**, **zipar** e **submeter** as atividades.

O Plugin foi desenvolvido através do programa de monitoria para a as atividades da disciplina "Laboratório de Programação II" do curso de Ciência da Computação na Universidade Federal de Campina Grande (UFCG).

## Documentação
Documentação disponível em https://submibot.firebaseapp.com

## Comandos

- **Submeter Projeto**:
Coleta as informações de sessão do usuário a partir de um token gerado pelo Canvas e solicita as informações do usuário para realizar a submissão. Em seguida, compacta o projeto em formato zip e realiza a submissão para uma atividade selecionada.

- **Localização do Projeto**:
Informa ao usuário o path/caminho onde o projeto selecionado se encontra.

- **Limpar Cache**:
O SubmiBot armazena informações básicas do usuário para as próximas submissões. Este comando limpa os arquivos em cache que contém estas informações.

## License
Licença disponível em: [MIT](https://github.com/hericlesme/SubmiBot/blob/master/LICENSE)
