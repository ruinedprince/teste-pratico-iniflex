# Teste Prático — Iniflex

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Dependências](https://img.shields.io/badge/depend%C3%AAncias-nenhuma-success)
![Status](https://img.shields.io/badge/status-conclu%C3%ADdo-blue)

Projeto Java puro (sem frameworks nem dependências externas) que resolve o teste prático de programação da Iniflex: cadastrar os funcionários de uma indústria e gerar os relatórios pedidos no enunciado.

---

## Estrutura

```
.
├── Pessoa.java        # Classe base: nome (String) e dataNascimento (LocalDate)
├── Funcionario.java   # Estende Pessoa: salario (BigDecimal) e funcao (String)
└── Main.java          # Classe principal: executa todos os requisitos em sequência
```

## Como executar

Pré-requisito: JDK 17 ou superior.

```bash
git clone https://github.com/ruinedprince/teste-pratico-iniflex.git
cd teste-pratico-iniflex

javac -encoding UTF-8 *.java
java Main
```

> No Windows, se os acentos aparecerem trocados no terminal, rode `chcp 65001` antes do `java Main`.

---

## Requisitos atendidos

| # | Requisito | Onde está |
|---|---|---|
| 1 | Classe `Pessoa` com `nome` e `dataNascimento` | [`Pessoa.java`](Pessoa.java) |
| 2 | Classe `Funcionario` estendendo `Pessoa`, com `salario` e `funcao` | [`Funcionario.java`](Funcionario.java) |
| 3 | Classe principal para executar as ações | [`Main.java`](Main.java) |
| 3.1 | Inserir os funcionários na ordem da tabela | `main` — lista `funcionarios` |
| 3.2 | Remover o funcionário "João" | `main` — `removeIf` |
| 3.3 | Imprimir todos os funcionários (data `dd/MM/yyyy`, valores com `.` no milhar e `,` no decimal) | `relatorioFuncionarios` |
| 3.4 | Aplicar 10% de aumento e atualizar a lista | `main` — `forEach` com `BigDecimal.multiply` |
| 3.5 | Agrupar por função em um `Map<String, List<Funcionario>>` | `funcionariosOrganizadosPorFuncao` |
| 3.6 | Imprimir os funcionários agrupados por função | `exibirFuncionariosPorFuncao` |
| 3.8 | Imprimir os aniversariantes dos meses 10 e 12 | `exibirAniversariantesOutubroEDezembro` |
| 3.9 | Imprimir o funcionário mais velho (nome e idade) | `exibirFuncionarioMaisVelho` — idade via `Period.between` |
| 3.10 | Imprimir a lista em ordem alfabética | `main` — `stream().sorted(Comparator.comparing(...))` |
| 3.11 | Imprimir o total dos salários | `main` — `stream().map(...).reduce(BigDecimal.ZERO, BigDecimal::add)` |
| 3.12 | Imprimir quantos salários mínimos (R$ 1.212,00) cada um ganha | `main` — `BigDecimal.divide` com `RoundingMode.HALF_UP` |

> O enunciado não tem item 3.7; a numeração acima segue a original.

---

## Decisões de implementação

- **`BigDecimal` para dinheiro.** Salários, aumento, soma e divisão pelo salário mínimo são calculados com `BigDecimal`, sem `double`, para não haver erro de arredondamento de ponto flutuante.
- **`LocalDate` e `Period` para datas.** A data de nascimento é um `LocalDate`, e a idade é calculada com `Period.between` em relação à data atual. Por isso ela acompanha o dia em que o programa roda.
- **Formatação no padrão brasileiro.** Datas usam `DateTimeFormatter.ofPattern("dd/MM/yyyy")`, e valores usam `NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))`, que gera `R$ 1.234,56`.
- **Dois estilos, de propósito.** Até o item 3.9, cada requisito ficou em um método próprio, no estilo clássico de orientação a objetos com laços explícitos. A partir do 3.10, passei a usar a **Stream API** e lambdas do próprio Java (`sorted`, `map`/`reduce`, `forEach`), que resolvem o requisito em poucas linhas, sem criar mais um método para cada item. Os dois estilos ficaram no código para mostrar as duas abordagens.

---

## Exemplo de saída (trecho)

```
====== FUNCIONÁRIO MAIS VELHO ======
Nome: Caio
Idade: 65 anos
------------------------------------
====== FUNCIONÁRIOS EM ORDEM ALFABÉTICA ======
  - Alice
  - Arthur
  - Caio
  - Heitor
  - Helena
  - Heloísa
  - Laura
  - Maria
  - Miguel
Soma total dos salários: R$ 50.906,82
====== QUANTIDADE DE SALÁRIOS MÍNIMOS ======
  - Maria ganha 1.82 salários mínimos.
  - Caio ganha 8.93 salários mínimos.
  - Miguel ganha 17.35 salários mínimos.
  ...
```

*A idade exibida depende da data em que o programa é executado.*
