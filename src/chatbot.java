import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class chatbot {
    // Arrays contendo as mensagens de saudação, despedida e FAQs
    private String[] greetings = {"Olá!", "Oi!", "Como posso ajudar você?"};
    private String[] farewells = {"Até logo!", "Tchau!", "Até mais!"};
    private String[] faqs = {
        "Qual é a sua política de privacidade?",
        "Como posso atualizar minhas informações de perfil?",
        "Posso cancelar minha assinatura?",
        "Como faço para redefinir minha senha?",
        "Quais são os métodos de pagamento aceitos?",
        "O que devo fazer se esquecer meu nome de usuário?",
        "Como posso entrar em contato com o suporte ao cliente?",
        "Vocês têm um programa de fidelidade?",
        "Onde posso encontrar informações sobre seus produtos/serviços?",
        "Como posso fazer uma reclamação?",
        "Qual é a previsão do tempo para hoje?"
    };
    
    // Respostas correspondentes às FAQs
    private String[] faqResponses = {
        "nossa política de privacidade pode ser encontrada em nosso site na seção 'Política de Privacidade'.",
        "você pode atualizar suas informações de perfil acessando a área 'Meu Perfil' em nosso site.",
        "sim, você pode cancelar sua assinatura a qualquer momento na seção 'Assinatura' do seu perfil.",
        "para redefinir sua senha, clique em 'Esqueci minha senha' na página de login e siga as instruções.",
        "aceitamos os seguintes métodos de pagamento: cartão de crédito, PayPal e boleto bancário.",
        "se você esquecer seu nome de usuário, clique em 'Esqueci meu nome de usuário' na página de login.",
        "você pode entrar em contato com o suporte ao cliente pelo e-mail suporte@exemplo.com ou pelo telefone 0800-123-456.",
        "sim, temos um programa de fidelidade! Visite a seção 'Fidelidade' em nosso site para mais informações.",
        "você pode encontrar informações sobre nossos produtos/serviços na seção 'Produtos' ou 'Serviços' em nosso site.",
        "para fazer uma reclamação, acesse a seção 'Contato' em nosso site e preencha o formulário de reclamação.",
        "a previsão do tempo para hoje indica que o dia está ensolarado."
    };
    
    // Palavras-chave para cada FAQ
    private String[][] faqKeywords = {
        {"politica", "privacidade"},
        {"atualizar", "informacoes", "perfil"},
        {"cancelar", "assinatura"},
        {"redefinir", "senha"},
        {"metodos", "pagamento"},
        {"esquecer", "nome", "usuario"},
        {"contato", "suporte", "cliente"},
        {"programa", "fidelidade"},
        {"informacoes", "produtos", "servicos"},
        {"reclamacao"},
        {"previsao", "tempo", "clima"}
    };

    // Nome do usuário
    private String userName;

    // Método para saudar o usuário
    public void greetUser() {
        System.out.println("Olá! Qual é o seu nome?");
        Scanner scanner = new Scanner(System.in);
        userName = scanner.nextLine();
        System.out.println("Olá, " + userName + "! Como posso ajudar você hoje?");
    }

    // Método para processar a entrada do usuário
    public void processInput(String input) {
        input = input.toLowerCase();

        // Verifica se a entrada contém "data" ou "hora" para fornecer a data e a hora atual
        if (input.contains("data") || input.contains("hora")) {
            getDateTime();
        } 
        // Verifica se a entrada contém "tchau" ou "adeus" para encerrar a conversa
        else if (input.contains("tchau") || input.contains("adeus")) {
            System.out.println(farewells[(int)(Math.random() * farewells.length)]);
        } 
        // Verifica se a entrada corresponde a uma FAQ
        else {
            boolean found = false;
            for (int i = 0; i < faqKeywords.length; i++) {
                for (String keyword : faqKeywords[i]) {
                    if (input.contains(keyword)) {
                        System.out.println("Pergunta reconhecida: " + faqs[i]);
                        System.out.println(userName + ", " + faqResponses[i]);
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (!found) {
                System.out.println("Desculpe, não entendi sua pergunta.");
            }
        }
    }

    // Método para obter e exibir a data e a hora atuais
    private void getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = now.format(formatter);
        System.out.println("Hoje é " + formattedDateTime + ".");
    }

    // Método principal para iniciar o chatbot
    public static void main(String[] args) {
        chatbot chatbot = new chatbot();
        chatbot.greetUser();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = scanner.nextLine();
            chatbot.processInput(userInput);
        } while (!userInput.equalsIgnoreCase("sair"));
    }
}
