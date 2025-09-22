# Plano de atividades 

As atividades devem ser implementadas na ordem em que aparecem neste plano. Os alunos podem se organizar em duplas.

## Grupo 01 de Atividades 

- [ ] Criar novo projeto Java que será usado em todas as atividades.
- [ ] Implementar TAD para os personagens, conforme características apresentadas em [readme.md](./readme.md). 
- [ ] Criar método que pede para o usuário indicar quais serão os dois personagens que irão competir.
- [ ] Criar instâncias dos dois objetos selecionados no item anterior, com os respectivos valores para seus atributos.

### Critérios de Aceitação

- [ ] Todas as classes utilizadas devem estar com a documentação padrão (descrição e nome dos autores).
- [ ] Os atributos do TAD de personagens devem ser privados.
- [ ] Um personagem não pode competir contra ele mesmo. Se o usuário violar esta regra, o sistema deve exibir mensagem de erro correspondente e solicitar que outro personagem seja selecionado para ser o jogador 2.
- [ ] Durante a seleção dos jogadores, o usuário deve poder também cancelar e terminar o jogo, sem selecionar os jogadores.   
- [ ] O programa deve exibir na tela quais foram os personagens selecionados, e quais são suas características.
- [ ] Deve-se utilizar try-with-resources para gerenciar o objeto do tipo Scanner.
- [ ] Deve-se fazer o devido tratamento de quaisquer exceções que possam ser lançadas. 

## Grupo 02 de Atividades 
- [ ] Criar repetição para as 5 rodadas.
- [ ] Determinar, por sorteio, qual tipo de pista em cada rodada.
- [ ] Em cada rodada, os jogadores devem simular o lançamento de um dado (número entre 1 e 6). Esse valor será usado posteriormente para calcular quem foi o vencedor da rodada. Para esse sorteio, reutilize o método que sorteia o tipo de pista.

### Critérios de Aceitação
- [ ] Programa exibe, para cada rodada, qual o tipo de pista e qual foi o valor que cada jogador tirou no dado.

## Grupo 03 de Atividades (etapa final)
- [ ] Acumular a pontuação de cada jogador, a cada rodada.

### Critérios de Aceitação
- [ ] Exibir, para cada rodada, quem foi o vencedor, se houver, e quantos pontos cada um fez.
- [ ] Exibir resultado final da corrida após a última rodada.
