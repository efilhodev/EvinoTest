package com.example.eduardojulio.evinotestapp.data;

import com.example.eduardojulio.evinotestapp.bean.Game;
import com.example.eduardojulio.evinotestapp.bean.GameInfo;
import com.example.eduardojulio.evinotestapp.data.response.TopGamesResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por adaptar a resposta do servidor remoto ao objeto local.
 * Created by Eduardo Julio on 3/21/2018.
 */

public class TopGamesDeserializer implements JsonDeserializer<TopGamesResponse>{
    //Isso poderia ser feito diretamente pelo SerializeName na classe Bean, porem como eu só quero algumas
    //linhas da resposta eu optei por criar um deserialize.


    @Override
    public TopGamesResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<GameInfo> data = new ArrayList<>();

        JsonArray group = json.getAsJsonObject().get("top").getAsJsonArray();
        for (JsonElement element : group) {

            GameInfo info = new GameInfo();
            info.setChannels(element.getAsJsonObject().get("channels").getAsInt());
            info.setViewers(element.getAsJsonObject().get("viewers").getAsInt());

            JsonObject jGameObj = element.getAsJsonObject().get("game").getAsJsonObject();
            Game game = new Game();
            game.setName(jGameObj.get("name").getAsString());
            game.setPopularity(jGameObj.get("popularity").getAsInt());
            game.setBoxURL(jGameObj.get("box").getAsJsonObject().get("large").getAsString());
            game.setLogoURL(jGameObj.get("logo").getAsJsonObject().get("large").getAsString());

            info.setGame(game);

            data.add(info);
        }
        return new TopGamesResponse(data);
    }
}
