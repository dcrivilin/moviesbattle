  
    create table filme (
       id bigint not null,
        nome varchar(255),
        nota_avaliacao double,
        pontuacao bigint,
        qtd_votos bigint,
        primary key (id)
    );

    
    create table jogador (
       id bigint not null,
        nome varchar(255),
        senha varchar(255),
        usuario varchar(255),
        primary key (id),
        UNIQUE KEY usuario (usuario)
    );
    
    create table rodada (
       id bigint not null,
	descricao varchar(255),
        primeiro_filme_id bigint,
        quiz_id bigint,
        resposta_id bigint,
        segundo_filme_id bigint,
        primary key (id)
    );
    
    create table quiz (
       id bigint not null,
        pontuacao integer,
        qtd_acerto integer,
        qtd_rodada integer,
        situacao varchar(255),
        jogador_id bigint,
        primary key (id)
    );
    
    create table ranking (
       id bigint not null,
        pontuacao integer,
        posicao integer,
        jogador_id bigint,
        primary key (id)
    );
    
    create table resposta (
       id bigint not null,
        descricao varchar(255),
        primary key (id)
    );
    
    alter table rodada 
       add constraint FK210qd1i3q8iljhrl9gbi7wq4q 
       foreign key (primeiro_filme_id) 
       references filme;
    
    alter table rodada 
       add constraint FKlwvla97ems7g7128ens03pp11 
       foreign key (quiz_id) 
       references quiz;
    
    alter table rodada 
       add constraint FK5pd9of4tmntr1w0q0n40spkld 
       foreign key (resposta_id) 
       references resposta;
    
    alter table rodada 
       add constraint FKr2crtb1s5d74pidaxi2htlobt 
       foreign key (segundo_filme_id) 
       references filme;
    
    alter table quiz 
       add constraint FK53baewi5v6b1tbhmv5giguumd 
       foreign key (jogador_id) 
       references jogador;
    
    alter table ranking 
       add constraint FKamhfm1qyme9svc4fgngx4h5tq 
       foreign key (jogador_id) 
       references jogador;