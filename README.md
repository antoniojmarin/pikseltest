# pikseltest
This project is based on Spring MVC, Rest api, using Springboot to run the application and run tests, H2 in memory database.

The SpringBootApplication class, PikselTestWebApplication is created to run and start the project, generating the controllers required.
Component class utils.AppStartupRunner is used to load json data files into H2 in memory database at start of the application, so the data is accesible for the required operations.

RestController RoyaltiesController defines the functions to recieve requests for the different endpoints needed.
This controller uses RoyaltiesService to implement those functions, that at its time, uses daos EpisodeRepository and StudioRepository to model database and get and save data.
In utils package, there's utility classes to model responseBody exit of the requests implemented.

Json data files are in resources/static/ json package, so the project can read them.

JUnit tests for the requests are defined in RoyaltiesControllerTests class, that show the success or fail of the main operations.
