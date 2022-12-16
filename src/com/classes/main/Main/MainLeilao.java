package com.classes.main.Main;

import com.classes.BO.ClienteBO;
import com.classes.BO.ItemBO;
import com.classes.BO.LeilaoBO;
import com.classes.BO.LeiloeiroBO;
import com.classes.DTO.*;
import com.classes.Util.Util;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

import static com.classes.Util.Util.*;

public class MainLeilao {
    static Cliente clienteLogado = new Cliente();
    static Leiloeiro funcionarioLogado = new Leiloeiro();

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Leilão do Jorginho");

        TipoUsuario tipoUsuario = null;

        System.out.println("Cliente ou funcionário?");
        //Escolher entre logar/registrar um funcionario ou cliente
        boolean tipoEscolhido = false;
        boolean loginEscolhido = false;
        do {
            System.out.println("1 - Cliente\n2 - Funcionário\nOutro - sair");
            int escolha = entrada.nextInt();
            entrada.nextLine();
            if (escolha == 1) {
                tipoUsuario = TipoUsuario.CLIENTE;
                tipoEscolhido = true;
            } else if (escolha == 2) {
                tipoUsuario = TipoUsuario.FUNCIONARIO;
                tipoEscolhido = true;
            } else {
                tipoEscolhido = true;
                loginEscolhido = true;
                logout();
            }
        } while (!tipoEscolhido);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //escolha de registrar nova conta ou de logar, caso logar ele sai
        //do loop principal, caso registrar, continua no loop principal
        //para ter a opção de logar com a nova conta
        while (!loginEscolhido) {
            System.out.println("Registrar nova conta ou logar?");
            System.out.println("1 - Registrar\n2 - Logar\nOutro - sair");
            int logreg = entrada.nextInt();
            entrada.nextLine();
            if (logreg == 1) {
                menuRegistro(tipoUsuario);
            } else if (logreg == 2) {
                menuLogin(tipoUsuario);
                loginEscolhido = true;
            } else {
                loginEscolhido = true;
                logout();
            }
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (clienteLogado != null || funcionarioLogado != null) {
            menuFuncoes(tipoUsuario);
        }
    }

    //menus de registro/login

    private static void menuRegistro(TipoUsuario tipoUsuario) {
        if (tipoUsuario == TipoUsuario.CLIENTE) {
            registroCliente();
        } else {
            registroFuncionario();
        }
    }

    private static void registroCliente() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Registro de Cliente");
        Cliente novo = new Cliente();

        System.out.print("Nome: ");
        novo.setNome(entrada.nextLine());

        long cpf = cpfRegistro();
        novo.setCpf(cpf);

        System.out.print("Data de Nascimento: ");
        novo.setDataNascimento(Date.valueOf(entrada.nextLine()));

        novo.setSalt(Util.getSalt());

        System.out.print("Senha: ");
        novo.setSenha(Util.criptografaSenha(entrada.nextLine(), novo.getSalt()));
        System.out.println("--------------------------");

        if (novo.registrar(novo)) {
            System.out.println("Registrado com sucesso");
        } else {
            System.out.println("Problema no registro, tente novamente");
        }

        entrada.close();
    }

    private static void registroFuncionario() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Registro de Leiloeiro");
        Leiloeiro novo = new Leiloeiro();

        System.out.print("Nome: ");
        novo.setNome(entrada.nextLine());

        long cpf = cpfRegistro();
        novo.setCpf(cpf);

        System.out.print("Data de Nascimento: ");
        novo.setDataNascimento(Date.valueOf(entrada.nextLine()));

        novo.setSalt(Util.getSalt());

        System.out.print("Senha: ");
        novo.setSenha(Util.criptografaSenha(entrada.nextLine(), novo.getSalt()));
        System.out.println("--------------------------");

        if (novo.registrar(novo)) {
            System.out.println("Registrado com sucesso");
        } else {
            System.out.println("Problema no registro, tente novamente");
        }

        entrada.close();
    }

    private static long cpfRegistro() {
        Scanner entrada = new Scanner(System.in);
        long cpf;
        while (true) {
            System.out.print("CPF: ");
            cpf = entrada.nextLong();
            entrada.nextLine();
            if (!checaCPF(cpf)) {
                System.out.println("CPF inválido, tente novamente");
                cpf = 0;
            } else {
                entrada.close();
                return cpf;
            }
        }
    }

    private static void menuLogin(TipoUsuario tipoUsuario) {
        if (tipoUsuario == TipoUsuario.CLIENTE) {
            clienteLogado = loginCliente();
        } else {
            funcionarioLogado = loginFuncionario();
        }
    }

    private static Cliente loginCliente() {
        Scanner entrada = new Scanner(System.in);
        ClienteBO clienteBO = new ClienteBO();
        Cliente cliente = new Cliente();
        System.out.println("Login de Usuário");

        long cpf;
        do {
            System.out.print("CPF: ");
            cpf = entrada.nextLong();
            entrada.nextLine();
            if (!checaCPF(cpf)) {
                System.out.println("CPF incorreto");
                cpf = 0;
            }
        } while (cpf == 0);

        byte[] salt = clienteBO.pesquisaCPF(cpf).getSalt();

        while (true) {
            System.out.print("Senha: ");
            String senha = Util.criptografaSenha(entrada.nextLine(), salt);

            if (cliente.logar(cpf, senha)) {
                cliente = clienteBO.pesquisaCPF(cpf);
                return cliente;
            } else {
                System.out.println("CPF ou senha incorretos");
            }
        }
    }

    private static Leiloeiro loginFuncionario() {
        Scanner entrada = new Scanner(System.in);
        LeiloeiroBO leiloeiroBO = new LeiloeiroBO();
        Leiloeiro leiloeiro = new Leiloeiro();
        System.out.println("Login de Leiloeiro");

        long cpf;
        do {
            System.out.print("CPF: ");
            cpf = entrada.nextLong();
            entrada.nextLine();
            if (!checaCPF(cpf)) {
                System.out.println("CPF incorreto");
                cpf = 0;
            }
        } while (cpf == 0);

        byte[] salt = leiloeiroBO.pesquisaCPF(cpf).getSalt();

        while (true) {
            System.out.print("Senha: ");
            String senha = Util.criptografaSenha(entrada.nextLine(), salt);

            if (leiloeiro.logar(cpf, senha)) {
                leiloeiro = leiloeiroBO.pesquisaCPF(cpf);
                return leiloeiro;
            } else {
                System.out.println("CPF ou senha incorretos");
            }
        }
    }

    //menu de funcoes disponíveis para o usuário logado

    public static void menuFuncoes(TipoUsuario tipoUsuario) {
        System.out.println("--------------------------");
        System.out.println("Menu - " + tipoUsuario.name().toLowerCase());

        if (tipoUsuario == TipoUsuario.CLIENTE) {
            menuCliente();
        } else {
            menuFuncionario();
        }
    }

    //funcoes disponíveis para cliente logado
    public static void menuCliente() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Funções");
        System.out.println("1 - Participar de Leilão");
        System.out.println("2 - Checar inventário");
        System.out.println("3 - Depositar");
        System.out.println("4 - Logout");
        Funcao funcao = Funcao.values()[entrada.nextInt() - 1];

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        escolheFuncaoCliente(funcao);
    }

    //funcoes disponíveis para leiloeiro logado
    public static void menuFuncionario() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Funções");
        System.out.println("1 - Organizar Leilão");
        System.out.println("2 - Checar estoque");
        System.out.println("3 - Checar Leilões Realizados");
        System.out.println("4 - Logout");
        Funcao funcao = Funcao.values()[entrada.nextInt() - 1];

        escolheFuncaoFuncionario(funcao);
    }

    public static void escolheFuncaoCliente(Funcao funcao) {
        switch (funcao) {
            case LEILAO -> leilaoCliente();
            case INVENTARIO -> inventario();
            case EXTRA -> depositar();
            case LOGOUT -> logout();
        }
    }

    public static void leilaoCliente() {

        boolean valendo = true;
        Item atual = geraItem();
        Random gera = new Random();
        double valorBase = atual.getItemValor();
        double fundos = clienteLogado.getOrcamento();


        System.out.println("Comeco de Leilão");
        System.out.println("Bem-vindos ao leilão de hoje");
        System.out.println("O item da noite de hoje é: " + atual.getItemNome());
        System.out.println("O lance inicial será de R$" + atual.getItemValor());

        int quantFakes = gera.nextInt(2, 6);
        List<ClienteFake> clienteFake = new ArrayList<>();
        for (int i = 0; i < quantFakes; i++) {
            clienteFake.add(geraClienteFake(atual.getItemValor()));
        }
        double lanceAtual = atual.getItemValor();

        List<BigDecimal> lances = new ArrayList<>();
        List<Object> lancadores = new ArrayList<>();
        List<ClienteFake> desistentes = new ArrayList<>();

        do {
            for (ListIterator<ClienteFake> iterator = clienteFake.listIterator(); iterator.hasNext(); ) {
                ClienteFake fake = iterator.next();
                if (fake.getLimite() > lanceAtual) {
                    double lance = fake.aleatorizaLance(lanceAtual);
                    if (lance == 0) {
                        System.out.println(fake.getNome() + " absteu-se dessa rodada");;
                        lancadores.add(fake);
                        lances.add(BigDecimal.valueOf(0));
                    } else {
                        lanceAtual = lance;
                        System.out.println(fake.getNome() + " faz um lance de R$" + lanceAtual);
                        lancadores.add(fake);
                        lances.add(BigDecimal.valueOf(lanceAtual));
                    }
                } else {
                    desistentes.add(fake);
                    System.out.println(fake.getNome() + " desistiu do leilão");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            clienteFake.removeAll(desistentes);
            System.out.println("Preço Base: R$" + valorBase + "  Ultimo lance: R$" + lanceAtual);

            double userLance = lanceUsuario(clienteFake, lanceAtual, fundos);

            if (userLance == -1) {
                valendo = false;
            } else if (userLance > 0) {
                lanceAtual = userLance;
                lancadores.add(clienteLogado);
                lances.add(BigDecimal.valueOf(lanceAtual));
            }

        } while (clienteFake.size() > 0 && valendo);

        if (clienteFake.size() == 0) {
            ItemBO itemBO = new ItemBO();
            ClienteBO clienteBO = new ClienteBO();

            System.out.println("Você venceu o leilão, parabens");
            System.out.println("Você se tornou o dono de " + atual.getItemNome());
            System.out.println("Você pagou R$" + lanceAtual + " por esse item");
            fundos -= lanceAtual;

            //diferencialValor recebe um valor aleatório
            //introduz possibilidade de prejuízo ou lucro
            double diferencialValor = lanceAtual * ((gera.nextInt(-20, 40)) / 100d);
            atual.setItemValor(lanceAtual + diferencialValor);
            System.out.println("Após conversar com um especialista " +
                    "você descobriu que o seu item vale " + atual.getItemValor());
            atual.setItemDono(clienteLogado.getId());
            clienteBO.atualizarFundos(fundos, clienteLogado.getId());
            itemBO.inserir(atual);
            LeilaoBO leilaoBO = new LeilaoBO();
            leilaoBO.inserir(lances, lancadores);
        } else {
            LeilaoBO leilaoBO = new LeilaoBO();
            leilaoBO.inserir(lances, lancadores);
        }
    }

    public static double lanceUsuario(List<ClienteFake> clienteFake, double lanceAtual, double fundos) {
        Scanner entrada = new Scanner(System.in);
        boolean lancando = true;
        while (lancando && clienteFake.size() > 0) {
            System.out.print("Deseja fazer um lance? (0 para pular o turno, -1 para desistir) ");

            double lance = entrada.nextDouble();
            if (lance == 0) {
                System.out.println("Você preferiu não fazer um lance nesse turno");
                return 0;
            } else if (lance > fundos) {
                System.out.println("Lance excede fundos disponíveis, fundos disponíveis: R$" + fundos);
            } else if (lanceAtual < lance && lance <= fundos) {
                lanceAtual = lance;
                return lanceAtual;
            } else if (lance < 0) {
                System.out.println("Desistiu do leilão");
                return -1;
            } else {
                System.out.println("Lance Inválido, por favor tente novamente");
            }
        }
        return 0;
    }

    public static void inventario() {
        Scanner entrada = new Scanner(System.in);
        ItemBO itemBO = new ItemBO();
        System.out.println("Visualização de inventário");
        List<Item> inventario = itemBO.pesquisarDono(clienteLogado.getId());
        for (Item item : inventario) {
            System.out.println("ID:" + item.getItemId());
            System.out.println("Nome: " + item.getItemNome());
            System.out.println("Valor: " + item.getItemValor());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Deseja vender algum de seus itens? (S/N)");
        if (entrada.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Digite o ID do item a ser vendido");
            int vendido = entrada.nextInt();
            if (clienteLogado.vender(vendido)) {
                System.out.println("Item vendido com sucesso, fundos adicionados");
            } else {
                System.out.println("Erro na venda, tente novamente");
            }
        }
    }

    public static void depositar() {
        Scanner entrada = new Scanner(System.in);
        ClienteBO clienteBO = new ClienteBO();
        clienteLogado.setOrcamento(clienteBO.puxaOrcamento(clienteLogado.getId()));

        System.out.println("Saldo Atual: " + clienteLogado.getOrcamento());
        System.out.println("Deseja fazer um depósito? (S/N)");
        if (entrada.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o valor do depósito: ");
            clienteLogado.setOrcamento(clienteLogado.getOrcamento() + entrada.nextDouble());
            System.out.println("Saldo Atual: " + clienteLogado.getOrcamento());
            clienteBO.atualizarFundos(clienteLogado.getOrcamento(), clienteLogado.getId());
        } else {
            System.out.println("Tenha um bom dia");
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logout() {
        clienteLogado = null;
        funcionarioLogado = null;
        System.out.println("Logout realizado com sucesso");
    }

    public static void escolheFuncaoFuncionario(Funcao funcao) {
        switch (funcao) {
            case LEILAO -> leilaoLeiloeiro();
            case INVENTARIO -> estoque();
            case EXTRA -> listaLeiloes();
            default -> logout();
        }
    }

    public static void leilaoLeiloeiro() {
        boolean valendo = true;
        Item atual = geraItem();
        Random gera = new Random();
        double valorBase = atual.getItemValor();
        double fundos = clienteLogado.getOrcamento();

        System.out.println("Comeco de Leilão");
        System.out.println("Bem-vindos ao leilão de hoje");
        System.out.println("O item da noite de hoje é: " + atual.getItemNome());
        System.out.println("O lance inicial será de R$" + atual.getItemValor());

        int quantFakes = gera.nextInt(3, 6);
        List<ClienteFake> clienteFake = new ArrayList<>();
        for (int i = 0; i < quantFakes; i++) {
            clienteFake.add(geraClienteFake(atual.getItemValor()));
        }
        double lanceAtual = atual.getItemValor();

        List<BigDecimal> lances = new ArrayList<>();
        List<Object> lancadores = new ArrayList<>();

        List<ClienteFake> desistentes = new ArrayList<>();
        do {
            for (ListIterator<ClienteFake> iterator = clienteFake.listIterator(); iterator.hasNext(); ) {
                ClienteFake fake = iterator.next();
                if (fake.getLimite() > lanceAtual) {
                    double lance = fake.aleatorizaLance(lanceAtual);
                    if (lance == 0) {
                        System.out.println(fake.getNome() + " absteu-se dessa rodada");
                        lancadores.add(fake);
                        lances.add(BigDecimal.valueOf(0));
                    } else {
                        lanceAtual = lance;
                        System.out.println(fake.getNome() + " faz um lance de R$" + lanceAtual);
                        lancadores.add(fake);
                        lances.add(BigDecimal.valueOf(lanceAtual));
                    }
                } else {
                    desistentes.add(fake);
                    System.out.println(fake.getNome() + " desistiu do leilão");
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            clienteFake.removeAll(desistentes);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Preço Base: R$" + valorBase + "  Ultimo lance: R$" + lanceAtual);

        } while (clienteFake.size() > 1);

        ItemBO itemBO = new ItemBO();
        ClienteBO clienteBO = new ClienteBO();

        System.out.println(clienteFake);

        System.out.println("O vencedor do leilão foi " + clienteFake.get(0).getNome());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("O vencedor se tornou o dono de " + atual.getItemNome());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Foi pago R$" + lanceAtual + " por esse item");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //diferencialValor recebe um valor aleatório
        //introduz possibilidade de prejuízo ou lucro
        double diferencialValor = lanceAtual * ((gera.nextInt(-20, 40)) / 100d);
        atual.setItemValor(lanceAtual + diferencialValor);
        System.out.println("Após conversar com um especialista " +
                "foi descoberto que o seu item vale " + atual.getItemValor());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LeilaoBO leilaoBO = new LeilaoBO();
        leilaoBO.inserir(lances, lancadores);
    }

    public static void estoque() {
        Scanner entrada = new Scanner(System.in);
        ItemBO itemBO = new ItemBO();
        System.out.println("Visualização de estoque");
        List<Item> inventario = itemBO.pesquisarTudo();
        for (Item item : inventario) {
            System.out.println("ID:" + item.getItemId());
            System.out.println("ID do Dono: " + item.getItemDono());
            System.out.println("Nome: " + item.getItemNome());
            System.out.println("Valor: " + item.getItemValor());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void listaLeiloes() {
        LeilaoBO leilaoBO = new LeilaoBO();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Listar leilões realizados");
        int id = entrada.nextInt();
        List<Leilao> leiloes = leilaoBO.puxaId(id);
        for (Leilao leiloe : leiloes) {
            System.out.println("Id leilão:" + leiloe.getIdLeilao());
            System.out.print(", Id transacao:" + leiloe.getIdTransacao());
            System.out.print(", Id usuario:" + leiloe.getIdUsuario());
            System.out.print(", Valor do lance:" + leiloe.getValorLance());
        }
    }
}
