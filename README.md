# Android crypto monitor

[![print-1.png](https://i.postimg.cc/BnHp35Bn/print-1.png)](https://postimg.cc/DSfLdXN9)
[![print-2.png](https://i.postimg.cc/wTW6cPtS/print-2.png)](https://postimg.cc/hfdWK21r)

Model 
O Model é um arquivo Kotlin onde definimos as classes que representam os dados recebidos da API. Quando a resposta da API chega em formato JSON, o Retrofit (biblioteca adicionada no build.gradle) é responsável por converter essa resposta diretamente em objetos Kotlin, evitando o trabalho manual de tratar o JSON.

Neste projeto, temos a classe TickerResponse, que representa toda a resposta da API. Dentro dela, existe o atributo ticker, do tipo Ticker, que corresponde à chave "ticker" no JSON.

A classe Ticker organiza as informações da cotação, como: preço máximo (high), preço mínimo (low), volume (vol), último preço de negociação (last), melhores valores de compra (buy) e venda (sell), além da data (date) em formato de número (Long).

Service
O arquivo Service/MercadoBitcoinService.kt define uma interface Kotlin que descreve a maneira como o aplicativo se comunica com a API para buscar a cotação do Bitcoin. Dentro dessa interface, o método getTicker() é marcado com a anotação @GET("api/BTC/ticker/"), o que indica que será realizada uma requisição GET para esse endpoint específico da API. A função é do tipo suspend, o que permite que seja executada de forma assíncrona utilizando coroutines, sem bloquear a execução principal do aplicativo. O método retorna um objeto do tipo Response<TickerResponse>, possibilitando acessar diretamente os dados da cotação do Bitcoin mapeados no formato definido no projeto.

Já o arquivo Service/MercadoBitcoinServiceFactory.kt é responsável por montar a configuração do Retrofit, ferramenta que realiza a comunicação com a API. Através do Retrofit.Builder(), é estabelecido o endereço base da API utilizando o método baseUrl(), que no caso aponta para "https://www.mercadobitcoin.net/". Em seguida, é adicionado o conversor de dados com addConverterFactory(GsonConverterFactory.create()), o que faz com que as respostas em formato JSON sejam automaticamente transformadas em objetos Kotlin usando o Gson. Após todas as configurações, o método build() é chamado para finalizar a criação do objeto Retrofit. Por último, é gerada uma instância da interface MercadoBitcoinService, permitindo que o aplicativo realize chamadas reais para a API utilizando a estrutura configurada.

Ui.theme
Esta pasta é responsável por armazenar todos os recursos visuais e de configuração utilizados no aplicativo, incluindo layouts de tela, textos, cores e imagens. Dentro dela encontramos:

layout - contém os arquivos de layout no formato .xml
values - armazena valores reaproveitáveis, como cores, textos e temas
drawable - utilizado para guardar imagens e formas (shapes)


