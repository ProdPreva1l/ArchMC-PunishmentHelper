package lol.arch.punishmenthelper.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import info.preva1l.CollectionHelper;
import lol.arch.punishmenthelper.PunishmentHelper;
import lol.arch.punishmenthelper.utils.TaskManager;
import lombok.experimental.UtilityClass;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.UUID;

@UtilityClass
public class OffenseHandler {
    private final CollectionHelper collectionHelper = PunishmentHelper.i().getCollectionHelper();

    public void addPlayerPunishment(UUID playerUUID, String reason) {
        int offense = getPlayerPunishment(playerUUID, reason);
        offense += 1;

        Document data = getPlayerData(playerUUID, reason);
        if (data == null) {
            Document doc = new Document("player", playerUUID.toString()).append("offense", offense).append("reason", reason);
            collectionHelper.insertDocument("punishment_handler", doc);
            return;
        }

        Bson updates = Updates.combine(Updates.set("offense", offense));
        collectionHelper.updateDocument("punishment_handler", data, updates);
    }

    public int getPlayerPunishment(UUID uuid, String reason) {
        Document data = getPlayerData(uuid, reason);
        if (data == null) return 1;
        return data.getInteger("offense");
    }

    public Document getPlayerData(UUID uuid, String reason) {
        MongoCollection<Document> collection = collectionHelper.getCollection("punishment_handler");
        Document filter = new Document("reason", reason).append("player", uuid.toString());
        return collection.find().filter(filter).first();
    }
}
