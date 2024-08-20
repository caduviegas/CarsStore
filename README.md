# Projeto CarStore

## 🎯 Tomada de Decisões

### Arquiteturas
Decidiu-se utilizar MVVM + Clean neste projeto para seguir os padrões atualmente utilizados no mercado de trabalho. Esta abordagem proporciona um código mais limpo, de fácil manutenção, entendimento e testes.

### Dados do Usuário
Para adquirir os dados do usuário, foi adicionado um fragmento onde ele pode inserir seu nome e e-mail para ser contactado futuramente.

### Validação dos Dados do Usuário
Para evitar o envio de dados inconsistentes para a API, o usuário só consegue se tornar um Lead quando seus dados forem validados como "válidos".

### Rotina de Envio de Leads
Na primeira tela do aplicativo, é disparada a funcionalidade de envio de leads, que consiste em:
1. Buscar no banco de dados local a lista de leads salvos.
2. Checar se essa lista não está vazia, para evitar envios de listas vazias para a API.
3. Caso a lista não esteja vazia, é feito o envio desses leads para a API.
4. Em caso de sucesso no envio de leads, o banco de dados local é limpo.

## ✔️ Técnicas e Tecnologias Utilizadas
- **Kotlin**: Linguagem utilizada no projeto.
- **ViewBinding**: Binding de views.
- **Flow**: Atualizações de tela em tempo real.
- **Coroutines**: Para executar ações demoradas em uma thread secundária.
- **Git**: Controle de versão do código no GitHub.
- **Room**: Persistência de dados em banco de dados interno com SQLite.
- **Retrofit**: Comunicação com a API.
- **Koin**: Injeção de dependência.
- **Arquitetura Clean + MVVM**: Estruturação do projeto.
- **Fragments**: Aplicativo é um SAP (Single Activity App).
- **Navigation Component**: Navegação entre fragments.
- **Moshi**: Parse do json pra kotlin.
- **State Pattern**
- **Observer Pattern**

## Melhorias Futuras do App
- Implementar Testes Unitários
- Implementar GitHub Actions
- Atualizar Layout de XML para Compose
- Modularizar o Projeto

---
