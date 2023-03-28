# Movies Battle Especificação
Crie uma API REST para uma aplicação ao estilo card game, onde serão informados dois filmes e o jogador deve acertar aquele que possui melhor avaliação no IMDB.
## Requisitos
1. O jogador deve fazer login para iniciar uma nova partida. Portanto, cada partida sempre será identificada pela autenticação do usuário.
a. Não há restrições onde armazenar os usuários: em memória, ou em banco, etc.
2. Cada rodada do jogo consiste em informar um par de filmes, observando para não repetir o mesmo par nem formar um par com um único filme.
a. São sequências não-válidas: [A-A] o mesmo filme repetido; [A-B, A-B] pares repetidos – considere iguais os pares do tipo A-B e B-A.
b. Os seguintes pares são válidos: [A-B, B-C] o filme B é usado em pares diferentes.
3. O jogador deve tentar acertar qual filme possui maior pontuação, composta pela nota (0.0-10.0) multiplicado pelo total de votos.
4. Se escolher o vencedor correto, conta 1 ponto. São permitidos até três erros. Após responder, terá acesso a novo par de filmes quando acessar o endpoint do quiz.
5. Forneça endpoints específicos para iniciar e encerrar a partida a qualquer momento. Valide o momento em que cada funcionalidade pode ser acionada.
6. Não deve ser possível avançar para o próximo par sem responder o atual.
7. Deve existir uma funcionalidade de ranking, exibindo os melhores jogadores e suas pontuações.
8. A pontuação é obtida multiplicando a quantidade de quizzes respondidos pela porcentagem de acerto.
## Não-Funcionais
1. Armazene os dados em H2 e preencha todas as tabelas necessárias.
2. Inicie os dados de sua aplicação usando webscraping ou a partir da API pública “http://www.omdbapi.com/” – levamos a sério que os dados sejam fidedignos.
3. Explore os frameworks Spring: Web, Boot, Data, Security e Cloud.
4. Linguagem: Java 11 ou 17
5. Escreva testes unitários (para validar as regras de negócio) e de integração (para validar a API). Cobertura de testes mínima: 80% dos métodos.
6. Não deixe de adicionar a documentação da API com base no OpenAPI 3.0.
7. Escolha a solução de autenticação que achar mais interessante. Crie pelo menos dois usuários/jogadores.
 
 
#Anotações do autor


Na construção desse app foi utilizado a [arquiterura hexagonal](https://medium.com/ssense-tech/hexagonal-architecture-there-are-always-two-sides-to-every-story-bc0780ed7d9c) para demonstração, uma vez que cada dia mais se fala em arquitetura limpa/código limpo. Também foi inserido o conceito de [arquitetura gritante](https://blog.dyegomaas.com.br/posts/artigo-arquitetura-gritante/), que visa criar objetos especializados de modo que fique claro seu objetivo só de ler o nome. 

No primeiro momento a arquitetura escolhida parece ter muitos objetos dificultando a manutenção. Mas o que é proporcionado é exatamente o contrário. O código fica bem desaclopado e de fácil manutenção.

Para o desenvolvimento da aplicação, foi criado um BDD com base na especificação do projeto. Essa é uma prática muito importante que orienta o desenvolvedor no entendimento e implementação do projeto.

Por se tratar de um projeto não produtivo, não foi usada nenhuma solução complexa para validação de usuário. Foi usado headers e banco de dados.

Devido ao tempo de desenvolvimento, existem alguns TODOS e melhorias a serem implementados.
- criação de logs inteligentes para observability;
- criação de exception mais estruturadas;
- criação interface no adapter.in para que a classe controller fique mais limpo (menos anotations);

# Teste

Foi implementado uma classe de teste integrado que testar os cenários significativos. 

- **fluxo completo com resposta corretas:** Inicio do quiz à solicitação do ranking;
- **fluxo completo com resposta incorreta:** Inicio do quiz ao termino por exceder a quantidade de erros aceitos;
- **validação do usuário:**  Valida se as solicitações pertence ao usuário logado. A aplicação permite jogos simultaneos por usuários diferentes e não permite que um jogador faça requisições a quiz não relacionado a ele.
  
Devido ao tempo e ao projeto não ser produtivo, não foram realizados testes unitários. Mas isso é uma premissa no dia a dia das squads e validado por ferramentas de devops.

#Demais informações

- **host:** http://localhost:8080/

- **rotas** http://localhost:8080/swagger-ui/index.html#/

- **h2 database** http://localhost:8080/h2-console

- **clase principal de testes:** OrquestracaoQuizServiceTest

#BDD - Desenvolvimento Orientado ao Comportamento

#####Cenário 1 - AUTENTICAR USUÁRIO AO CRIAR QUIZ

dado que
 - foi informado um usuário e senha
 - foi solicitado um novo quiz
 
quando
 - for feita uma requisição para criar um novo quiz

então
 - será verificada a autenticação do usuário no banco de dados
 - será realizada a liberação continuidade do processo
 - um novo quiz/partida será criado 

#####Cenário 2 - AUTENTICAR USUÁRIO NAS DEMAIS ROTAS

dado que
 - foi informado um usuário e senha
 
quando
 - for feita uma requisição para as demais rotas do quiz

então
 - será verificada a autenticação do usuário no banco de dados
 - será verifiado se o quiz informado pertence ao usuário informado
 - será realizada a liberação continuidade do processo
 
#####Cenário 3 - INICIAR NOVO QUIZ/PARTIDA 

dado que
 - não há quiz/partida em andamento para aquele usuário
 
quando
 - for solicitado o início de uma nova partida

então
 - um novo quiz/partida será criado com 25 rodadas (50 filmes adicionados na base) respeitando os requisitos da sessão 2
 - os dados serão persistidos na base
 
#####Cenário 4 - SOLICITAR NOVA RODADA quando HOUVER RODADAS PENDENTES

dado que
 - o quiz/partida está 'em andamento'
 - e existem rodadas pendentes de resposta

quando
 - ao solicitar uma nova rodada do quiz

então
 - será retornada a rodada pendente de acordo com a ordenação no banco de dados
 
#####Cenário 5 - SOLICITAR NOVA RODADA quando NÃO HOUVER RODADAS PENDENTES

dado que
 - o quiz/partida está 'em andamento'
 - e não existem rodadas pendentes de resposta

quando
 - ao solicitar uma nova rodada do quiz

então
 - será verificado se há rodadas a serem respondidas, se não, será solicitado o encerramento do quiz/partida
 
#####Cenário 6 - RESPONDER UMA RODADA CORRETAMENTE

dado que
 - o quiz/partida está 'em andamento'
 - foi informada uma resposta

quando
 - o jogador responder a rodada corretamente

então
 - será somado 1 ponto ao quiz/partida
 - a rodada será atualizada com a resposta 1 - RESPOSTA_CORRETA
  
#####Cenário 7 - RESPONDER UMA RODADA INCORRETAMENTE

dado que
 - o quiz/partida está 'em andamento'
 - foi informada uma resposta

quando
 - o jogador responder a rodada incorretamente

então
 - a rodada será atualizada com a resposta 1 - RESPOSTA_INCORRETA
 - será verificado a quantidade de erros relacionado ao quiz/partida. Se a quantidade de erro for => a 3, será solicitado o encerramento do quiz/partida
 
#####Cenário 8 - ENCERRAR QUIZ/PARTIDA

dado que
 - o quiz/partida está 'em andamento'

quando
 - for solicitado o encerramento do quiz/partida

então
 - se houver rodadas pendentes de resposta, elas serão atualizadas para resposta 4 - 'ENCERRADA_SEM_RESPOSTA')
 - a situação do quiz/partida será atualizado para 'ENCERRADO'
 
#####Cenário 9 - SOLICITAR RANKING

dado que
 - existem quizzes concluidos 
 
quando
 - for solicitado ranking

então
 - a pontuação do jogador será obtida multiplicando a quantidade de quizzes respondidos pela porcentagem de acerto
 - 	será exibido o ranking ordenado de forma decrescente pela pontuação do jogador
