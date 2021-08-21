docker build . -t appointment
docker run --name appointment-container -p 8080:8080 appointment