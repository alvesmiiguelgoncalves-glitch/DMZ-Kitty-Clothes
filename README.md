# DMZ S.A.B. Jacket

Addon para o **DragonMine Z 2.2** (Forge 1.20.1) que adiciona a **Jaqueta S.A.B.** como item de **Curios**, num slot próprio chamado "Jaqueta". Assim você usa a jaqueta junto com qualquer armadura ou roupa, e ela desenha também a gola (cabeça) e a cobertura das coxas (pernas).

**Créditos:** modelo e texturas da jaqueta por **Novatsugs** (*Novatsugs Extra Clothes*), usados com permissão do criador.

## Instalar

1. Suba esta pasta num repositório do GitHub e rode o workflow **Build** (aba Actions).
2. Baixe o `.jar` do artifact e coloque na pasta `mods`, junto com o DragonMine Z 2.2 e o Curios (que o DMZ já exige).

## Usar

- **Obter:** aba de criativo **DMZ Jaqueta S.A.B.** (ou `/give @s dmz_sab_jacket:sab_jacket`).
- **Equipar:** na tela do Curios, slot **Jaqueta**.
- **Tingir:** mesa de crafting, jaqueta + corantes (igual armadura de couro, dá para misturar corantes). Sem tinta ela é azul (textura original); com tinta usa a textura cinza do criador com a cor aplicada.
- **Tirar a tinta:** clique com a jaqueta num caldeirão com água.

## Notas e limitações

- Não usa mixin. O DMZ já repassa as camadas de terceiros (inclusive a do Curios) ao renderer dele.
- O DMZ não desenha camadas de terceiros em formas Oozaru; a jaqueta some nessas formas.
- O modelo foi convertido do Bedrock (`geometry.overcoat`). As caixas do peito (`breasts`) têm uma rotação de -37,5°; se o peito estiver inclinado para o lado errado, é a única parte onde o sinal da conversão pode estar invertido.
- Ainda não foi compilado nem testado no jogo.
