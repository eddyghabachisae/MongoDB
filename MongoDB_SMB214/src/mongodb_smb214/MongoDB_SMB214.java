/*
 * Application sur MongoDB
 * Fait partie du projet du cours SMB214 à l'ISAE/CNAM
 * Réalisé par Eddy Ghabach
 */

package mongodb_smb214;

/* 
importation des classes de la bibliotheque de MongoDB
apres l'avoir ajouté dans le repertoire "librairies"
*/

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.io.IOException;

/**
 *
 * @author Eddy
 */

public class MongoDB_SMB214 {
    
    //la classe main
    public static void main(String[] args) {
        
        // exeception en cas d'erreur
        
        try {
            
        //connection à MongoDB
        
        MongoClient mongoClient = new MongoClient( "localhost" );
    
        //choix de la base de données; dans notre exemple on a la base de données isae
        
        DB db = mongoClient.getDB( "isae" );
    
        
        //choix de la collection; on a deja créé une collection inscription qui contient le cours SMB214
        
        DBCollection coll = db.getCollection("inscription");
        
        //la fonction findOne() va nous permettre te rechercher le premier document dans la
        //collection inscription
        
        DBObject myDoc = coll.findOne();
        
        //le systeme va afficher le premier document: smb214, qu'on a ajouté avant
        
        System.out.println(myDoc);
        
        //on ajoute un document contenant les infos du cours ENG210
        
        BasicDBObject doc1 = new BasicDBObject("Code", "ENG210").
                              append("Nom", "Exercer le metier d'ingenieur").
                              append("Credits", 6);

        coll.insert(doc1);
        
        //on ajoute un document contenant les infos du cours ENG221
        
        BasicDBObject doc2 = new BasicDBObject("Code", "ENG221").
                              append("Nom", "Information et communication pour ingenieur").
                              append("Credits", 6);

        coll.insert(doc2);
        
        //le systeme va imprimer le nombre de documents dans la collection inscription

        System.out.println(coll.getCount());
        
        // le systeme va afficher tous les documents de la collection inscription
        
        DBCursor cursor = coll.find();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }
      
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        
    } 
}
