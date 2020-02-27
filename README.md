# PLR

Uma aplicação para gerenciamento de metas de funcionários.

## Tecnologias

*	Java 8
*	Spring Boot 2.1.1
*	Maven

## Configuração 

Configure o ambiente que deseja utilizar no arquivo src/main/resources/application.properties. O Spring Boot aplicará as configurações
setadas no respectivo arquivo propertie indicado no parâmetro *spring.profiles.active*:

```java
# Config file
app.version=2.4.1

spring.profiles.active=prod

# Files
app.plr.export.template.folha.meta.path = /opt/plr/templates/TEMPLATE_FOLHA_METAS.xlsx
app.plr.export.template.indicadores.path = /opt/plr/templates/TEMPLATE_INDICADORES.xlsx
app.plr.export.template.colaboradores.path = /opt/plr/templates/TEMPLATE_COLABORADORES.xlsx
```

## Arquivos

A API consegue exportar algumas informações no formato Excel(*.xlsl*). Os templates ficam situados no seguinte diretório:

```
/opt/plr/templates/
```

Os templates atuais são:

*	**TEMPLATE_FOLHA_METAS.xlsx**: exporta valores referentes as folhas de metas de cada colaborador.
*	**TEMPLATE_INDICADORES.xlsx**: exporta a tabela de cadastro de indicadores.
*	**TEMPLATE_COLABORADORES.xlsx**: exporta a tabela de cadastro dos colaboradores.

## Deploy

Construa o arquivo *.war* que será deployado no tomcat, usando o seguinte comando Maven:

```java
mvn clean package
```

O nome do arquivo a ser gerado na pasta *target* deverá ser:

```
plr-api.war
```

Na pasta target, transfira o arquivo *.war* gerado no build e 

```
scp /dir/para/projeto/target/plr-api.war suporte@172.16.0.16:/dir/destino
```

Navegue até o diretório do tomcat. Pare o serviço:

```
cd /opt/tomcat/apache-tomcat-9.0.14
sh ./bin/shutdown.sh
```

Na pasta webapp do tomcat, remova os arquivos *plr-api.war* e a pasta *plr-api*:

```
rm -rf webapps/plr-api*
```

Ainda no tomcat, copie o arquivo *plr-api.war*, transferido anteriormente para o servidor:

```
cp -R ~/dir/destino/plr-api.war webapps/
```

Reinie o serviço:

```
sh ./bin/startup.sh
```

Confira o acesso em:

```
172.16.0.16:8080/planometas
```