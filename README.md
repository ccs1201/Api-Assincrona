# Api-Assincrona
Async Requests tests whit CompletableFuture

Neste exemplo temos o seguinte cenário:

* API com dois endpoints um síncrono e um assíncrono, em ambos temos uma "pausa" de 10 segundos no processamento da resposta, simulando uma "tarefa" demorada (com longo tempo de execução).

* Os métodos registram no console o tipo de requisição recebida síncrona/assíncrona, data hora do recebimento e data hora da finalização (término do processamento).

* O Tomcat embedded está configurando para rodar com somente uma thread (server.tomcat.threads.max=1), para simular um cenário de esgotamento do pool.

* Para o método síncrono o retorno é uma String e para o assíncrono, o pulo do gato, ele retorna um CompletableFuture<String> do pacote java.util.concurrent

* Para este teste foi utilizada a ferramenta K6 link (https://k6.io/)

* Cenário de teste: 10 VUs (Virtual Users) com duração de 100 Segundos e 30 Segundos de período de graça (tempo que será esperado antes de matar as requests não concluídas) para ambos os casos.
 
* Os scripts de teste estão em src/main/resources/teste_scripts caso você queira baixar e fazer seus próprios testes.
