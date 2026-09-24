import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.time.Period;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Criando a nossa lista ("base de dados") para armazenar os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        Funcionario funcionario;
        // 1. Maria
        funcionario = new Funcionario();
        funcionario.nome = "Maria";
        funcionario.dataNascimento = LocalDate.of(2000, 10, 18);
        funcionario.salario = new BigDecimal("2009.44");
        funcionario.funcao = "Operador";
        funcionarios.add(funcionario);
        // 2. João
        funcionario = new Funcionario();
        funcionario.nome = "João";
        funcionario.dataNascimento = LocalDate.of(1990, 5, 12);
        funcionario.salario = new BigDecimal("2284.38");
        funcionario.funcao = "Operador";
        funcionarios.add(funcionario);
        // 3. Caio
        funcionario = new Funcionario();
        funcionario.nome = "Caio";
        funcionario.dataNascimento = LocalDate.of(1961, 5, 2);
        funcionario.salario = new BigDecimal("9836.14");
        funcionario.funcao = "Coordenador";
        funcionarios.add(funcionario);
        // 4. Miguel
        funcionario = new Funcionario();
        funcionario.nome = "Miguel";
        funcionario.dataNascimento = LocalDate.of(1988, 10, 14);
        funcionario.salario = new BigDecimal("19119.88");
        funcionario.funcao = "Diretor";
        funcionarios.add(funcionario);
        // 5. Alice
        funcionario = new Funcionario();
        funcionario.nome = "Alice";
        funcionario.dataNascimento = LocalDate.of(1995, 1, 5);
        funcionario.salario = new BigDecimal("2234.68");
        funcionario.funcao = "Recepcionista";
        funcionarios.add(funcionario);
        // 6. Heitor
        funcionario = new Funcionario();
        funcionario.nome = "Heitor";
        funcionario.dataNascimento = LocalDate.of(1999, 11, 19);
        funcionario.salario = new BigDecimal("1582.72");
        funcionario.funcao = "Operador";
        funcionarios.add(funcionario);
        // 7. Arthur
        funcionario = new Funcionario();
        funcionario.nome = "Arthur";
        funcionario.dataNascimento = LocalDate.of(1993, 3, 31);
        funcionario.salario = new BigDecimal("4071.84");
        funcionario.funcao = "Contador";
        funcionarios.add(funcionario);
        // 8. Laura
        funcionario = new Funcionario();
        funcionario.nome = "Laura";
        funcionario.dataNascimento = LocalDate.of(1994, 7, 8);
        funcionario.salario = new BigDecimal("3017.45");
        funcionario.funcao = "Gerente";
        funcionarios.add(funcionario);
        // 9. Heloísa
        funcionario = new Funcionario();
        funcionario.nome = "Heloísa";
        funcionario.dataNascimento = LocalDate.of(2003, 5, 24);
        funcionario.salario = new BigDecimal("1606.85");
        funcionario.funcao = "Eletricista";
        funcionarios.add(funcionario);
        // 10. Helena
        funcionario = new Funcionario();
        funcionario.nome = "Helena";
        funcionario.dataNascimento = LocalDate.of(1996, 9, 2);
        funcionario.salario = new BigDecimal("2799.93");
        funcionario.funcao = "Gerente";
        funcionarios.add(funcionario);
        // 3.2 - Remover o funcionario "João" da lista.
        funcionarios.removeIf(f -> f.nome.equals("João"));
        relatorioFuncionarios(funcionarios);
        /*
        3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        • informação de data deve ser exibido no formato dd/mm/aaaa;
        • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        */
        relatorioFuncionarios(funcionarios);
        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(f -> f.salario = f.salario.multiply(new BigDecimal("1.10")));
        relatorioFuncionarios(funcionarios);
        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> mapaPorFuncao = funcionariosOrganizadosPorFuncao(funcionarios);
        // 3.6 – Imprimir os funcionários, agrupados por função.
        exibirFuncionariosPorFuncao(mapaPorFuncao);
        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.3
        exibirAniversariantesOutubroEDezembro(funcionarios);
        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        exibirFuncionarioMaisVelho(funcionarios);
        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("====== FUNCIONÁRIOS EM ORDEM ALFABÉTICA ======");
        funcionarios.stream().sorted(Comparator.comparing(f -> f.nome)).forEach(f -> System.out.println("  - " + f.nome));
        // 3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream().map(f -> f.salario).reduce(BigDecimal.ZERO, BigDecimal::add);
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.println("Soma total dos salários: " + formatadorMoeda.format(totalSalarios));
        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        System.out.println("====== QUANTIDADE DE SALÁRIOS MÍNIMOS ======");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("  - " + f.nome + " ganha " + qtdSalariosMinimos + " salários mínimos.");
        });
    }
    public static void relatorioFuncionarios(List<Funcionario> funcionarios) {
        // 3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela.
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.nome);
            System.out.println("Data de nascimento: " + f.dataNascimento.format(formatadorData));
            System.out.println("Salário: " + formatadorMoeda.format(f.salario));
            System.out.println("Função: " + f.funcao);
            System.out.println("--------------------------------");
        }
        System.out.println("Quantidade de funcionários: " + funcionarios.size());
        System.out.println("--------------------------------");
    }
    public static Map<String, List<Funcionario>> funcionariosOrganizadosPorFuncao(List<Funcionario> lista){
        Map<String, List<Funcionario>> mapa = new HashMap<>();
        for (Funcionario f : lista) {
            String cargo = f.funcao;
            if (!mapa.containsKey(cargo)) {
                mapa.put(cargo, new ArrayList<>());
            }
            mapa.get(cargo).add(f);
        }
        return mapa;
    }
    public static void exibirFuncionariosPorFuncao(Map<String, List<Funcionario>> mapa) {
        for (String cargo : mapa.keySet()) {
            System.out.println("====== FUNÇÃO: " + cargo.toUpperCase() + " ======");
            List<Funcionario> funcionariosDoCargo = mapa.get(cargo);
            for (Funcionario f : funcionariosDoCargo) {
                System.out.println("  - " + f.nome);
            }
        }
    }
    public static void exibirAniversariantesOutubroEDezembro(List<Funcionario> lista) {
        System.out.println("====== ANIVERSARIANTES DOS MESES 10 E 12 ======");
        for (Funcionario f : lista) {
            int mesNascimento = f.dataNascimento.getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                System.out.println("Nome: " + f.nome + " | Data de Nasc.: " + f.dataNascimento);
            }
        }
    }
    public static void exibirFuncionarioMaisVelho(List<Funcionario> lista) {
        if (lista.isEmpty()) return;
        Funcionario maisVelho = lista.get(0);
        for (Funcionario f : lista) {
            if (f.dataNascimento.isBefore(maisVelho.dataNascimento)) {
                maisVelho = f;
            }
        }
        int idade = Period.between(maisVelho.dataNascimento, LocalDate.now()).getYears();
        System.out.println("====== FUNCIONÁRIO MAIS VELHO ======");
        System.out.println("Nome: " + maisVelho.nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("------------------------------------"); 
    } 
}
