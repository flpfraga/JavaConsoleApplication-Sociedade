Projeto Java Banco BDMG

Este projeto foi realizado como prova pratica para processo seletivo.

Premissa:
No Banco XPTO, para o cálculo do comprometimento financeiro de uma empresa, leva-se em conta o total de bens imóveis desta empresa e de seus sócios. Escreva um método (e todas as classes adicionais) para retornar o total do comprometimento financeiro de uma empresa, passando como parâmetro a sua estrutura societária.

Requisitos do negócio:
• A estrutura societária de uma empresa é sempre composta por ao menos uma pessoa física ou jurídica e pode ser composta por mais pessoas físicas e/ou jurídicas.
• Toda empresa possui uma estrutura societária.
• Pode haver ciclo societário, ou seja, a empresa A pode ser sócia da empresa B e a empresa B pode vir a ser sócia da empresa A. Inclusive, a empresa A pode vir a ser sócia dela mesma. Além disso, qualquer pessoa física ou jurídica pode ser sócia de mais de uma empresa.
• A contabilização dos bens imóveis de uma pessoa (física ou jurídica) só deve ocorrer uma única vez.

Foi utilizado:
Java 17
Maven
Lombok
Hibernate Validator
Java Console Application
