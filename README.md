# Uploadify-Backend
This Spring Boot API allows users to upload files and generate short links for easy sharing. It uses MongoDB as the database to store file information and short links, Cloudinary for file upload and JWT for authentication. 

# Tech stack
* Spring boot
* MongoDB
* JWT
* Cloudinary

# Setup
1. Clone the repository:
   ```
   git clone <repository-url>
   cd Backend
   ```
2. Configure MongoDB and Cloudinary:
   ```
   spring.data.mongodb.uri=<mongodb-host>
   spring.data.mongodb.database=<mongodb-database>
   
   cloudinary.cloud-name=<cloudinary-cloud-name>
   cloudinary.api-key=<cloudinary-api-key>
   cloudinary.api-secret=<cloudinary-api-secret>
   ```
3. Build and run the application:
   ```
   mvn clean install
   mvn spring-boot:run
   ```

# End points
1. POST /api/authentication/register
   Request
   ```
   {
       "name": "john",
       "email": "john@gmail.com"
       "password": "Abcd1234#"
   }
   ```
   Response
   ```
   {
      "access_token": "ACCESS_TOKEN"
   }
   ```
3. POST /api/authentication/login
   Request
   ```
   {
       "email": "john@gmail.com"
       "password": "Abcd1234#"
   }
   ```
   Response
   ```
   {
      "access_token": "ACCESS_TOKEN"
   }
   ```
4. POST /api/files/upload
   Request
   ```
   {
       "file": "FILE"
   }
   ```
   Response
   ```
   {
       "id": "65415a0da309661cb29d9955",
       "url": NULL,
       "type": "jpg",
       "size": 156256,
       "publicId": "f1378229-fa78-441c-b566-a67adf3ba0f2",
       "userId": "6541587aa309661cb29d9947",
       "mediaType": "image/jpeg",
       "fileName": "0Eoyaz8.jp",
       "expiresAt": "2023-11-01T19:48:29.808+00:00",
       "createdAt": "2023-10-31T19:48:29.811+00:00",
       "updatedAt": "2023-10-31T19:48:29.811+00:00"
   }
   ```
5. GET /api/files/{publicId}
   Response
   ```
   {
       "id": "65415a0da309661cb29d9955",
       "url": "https://res.cloudinary.com/dh96vxa5a/image/upload/v1698781710/f1378229-fa78-441c-b566-a67adf3ba0f2.jpg",
       "type": "jpg",
       "size": 156256,
       "publicId": "f1378229-fa78-441c-b566-a67adf3ba0f2",
       "userId": "6541587aa309661cb29d9947",
       "mediaType": "image/jpeg",
       "fileName": "0Eoyaz8.jp",
       "expiresAt": "2023-11-01T19:48:29.808+00:00",
       "createdAt": "2023-10-31T19:48:29.811+00:00",
       "updatedAt": "2023-10-31T19:48:29.811+00:00"
   }
   ```
6. POST /api/files
   Response
   ```
   [
     {
         "id": "65415a0da309661cb29d9955",
         "url": NULL,
         "type": "jpg",
         "size": 156256,
         "publicId": "f1378229-fa78-441c-b566-a67adf3ba0f2",
         "userId": "6541587aa309661cb29d9947",
         "mediaType": "image/jpeg",
         "fileName": "0Eoyaz8.jp",
         "expiresAt": "2023-11-01T19:48:29.808+00:00",
         "createdAt": "2023-10-31T19:48:29.811+00:00",
         "updatedAt": "2023-10-31T19:48:29.811+00:00"
     }
   ]
   ```
7. DELETE /api/files/{id}
