pipeline {
    agent any

    environment {
        // Define el nombre del proyecto de Docker Compose aquí
        PROJECT_NAME = 'sgu-rvrh-10c'
    }

    stages{
        // parar todos los servicios
        stage('Parando los servicios') {
            steps{
                // Usamos 'bat' y la sintaxis '|| exit /b 0' para ignorar errores
                // Se actualiza el nombre del proyecto a %PROJECT_NAME%
                bat ''' 
                    docker compose -p %PROJECT_NAME% down || exit /b 0
                '''
            }
        }

        // Eliminar imagenes anteriores
        stage('Borrando imagenes antiguas') {
            steps{
                // Usamos el bucle 'for /f' de Batch y la lógica de 'errorlevel'
                // Se actualiza el filtro de label al nuevo %PROJECT_NAME%
                bat '''
                    for /f "tokens=*" %%i in ('docker images --filter "label=com.docker.compose.project=%PROJECT_NAME%" -q') do (
                        docker rmi -f %%i
                    )
                    if errorlevel 1 (
                        echo No hay imagenes por eliminar
                    ) else (
                        echo Imagenes eliminadas correctamente
                    )
                '''
            }
        }

        //Bajar la actualizacion
        stage('Actualizando...') {
            steps{
                checkout scm
            }
        }

        //Levantar y desplegar el proyecto
        stage('Levantando y desplegando el proyecto') {
            steps{
                // Usamos 'bat' para el comando final
                // Se actualiza el comando up para usar el %PROJECT_NAME%
                bat '''
                    docker compose -p %PROJECT_NAME% up --build -d
                '''
            }
        }
    }

    post{
        success{
            echo 'Pipeline ejecutado exitosamente'
        }
        failure{
            echo 'Error en la ejecución del pipeline'
        }
        always{
            echo 'Fin del pipeline'
        }
    }
}


// Para jenkis 
// 1. entrar a jenkis y en nueva tarea 
// 2. poner nombre y seleccionar pipeline
// 3. en configuracion del pipeline solo seleccionar github hook trigger
// 4. en Definition seleccionar pipeline script from scm
// 5. en SCM seleccionar git
// 6. en Repository URL poner la url del repositorio
// 7. en Credentials seleccionar las credenciales creadas
// 8. en Branches to build poner main o master
// 9. en script path poner Jenkinsfile
// 10. guardar

//constuir proyecto en jenkins
// dentro del proyecto seleccionar "Construir ahora"
// aparece la construccion, podemos seleccionarla
// entrar a Pipeline Overview para ver el avance de la construccion



//credenciales
// si no hay credenciales seleccionar add y jenkins
// todo lo demas dejar por defecto
// en username poner el usuario GH: blogNotas
// en password poner el tokeerrn de GH: blogNotas
// en ID le ponemos como se van a llamar las credenciales ejemplo: ValentinGitHub
// le damos en add y ya se agregan las credenciales