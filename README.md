# **Relatório do projeto final**
#### **Linguagem de Programação I**
## Alunos: 
### Cauã Gomes de Melo - 20210025922
### Jorge Alberto M. C. de Oliveira - 20210026886
### Pedro Augusto Dias de Lima - 20210026400
  
### *Sistema de Mercado em Java*

##### Cauã Gomes de Melo
##### Jorge Alberto Oliveira
##### Pedro Augusto Dias de Lima

###### Código fonte armazenado no GitHub

## Introdução
Neste projeto, nosso grupo implementou um sistema de compra e venda de um mercado ou loja específica, utilizando programação orientada a objeto com a linguagem java.  
Com uma interface gráfica desenvoldida, podem se cadastrar (com nome de usuário e senha.) dois tipos de usuário, cliente e administrador, onde o cliente pode fazer pedidos de items e adicionar-los ao seu carrinho, baseado em um estoque existente. Pode também esvaziar o carrinho e fechar-lo para conferir o valor total dos produtos nele.   
Já o administrador tem a capacidade de alterar o estoque, cadastrando novos items e também removendo-os.

## Modelagem do Problema
![Diagrama](/Classe%20UML.png) 
Sobre as relações entre classes, abstraímos o problema para que tanto a classe Estoque quanto Carrinho são compostos por atributos de ArrayLists (da framework Collections) de Item, sendo essa classe composta por um produto e sua quantidade.  
Por questões de encapsulamento, deixamos seus atributos como privados e desenvolvemos contrutores e métodos getters e setters para manipulá-los.  
Na seção de login, nosso grupo não julgou prático o uso de interface, e para poder melhor aplicar o conceito de herança decidimos que uma classe abstrata seria satisfatório.  
Essa classe abstrata Usuário, possui atributos que representam nome do usuário e senha, além de uma função de logout. As classes Cliente e Administrador herdam dessa classe abstrata e extendem essas características em seu própio código.  
Certas classes (Carrinho, Estoque, Cliente e Administrador) utilizam de polimorfismo em métodos onde se utiliza a sobrecarga de parâmetros para mudar sua execução.  
Por exemplo: a classe Administrador ao usar o método removerItemDoEstoque(), inserindo em seu parâmetro apenas o item desejado, esse será completamente removido do estoque. Mas caso se usar a mesma função, inserindo o item e a quantidade, será subtraído apenas a quantidade inserida do item.


## Ferramentas Utilizadas  
Nesse projeto, para programar foram utilizados diversas IDEs, sendo elas:
* Eclipse
* IntelliJ
* Visual Code Studio  
* LucidChart
* Além de também se utilizar o site markdownlivepreview.com, para ajudar na formulação deste relatório em formato markdown.

## Conclusões e Considerações Finais  
Após uma longa resolução de problemas, além de uma implementação de interface gráfica, o programa em geral apresentou bons resultados.  
A idéia do programa pode ser implementada, e foi parcialmente inspirada, no gigante comércio digital contemporâneo, que cada vez mais possui maior papel na sociedade.  
A disciplina de Linguagem de Programação I, onde se foi ensinado a linguagem java, foi de grande importância, visto que, atualmente linguagens de programação orientada a objeto, incluindo java, são bastante requisitadas no mercado de trabalho.
