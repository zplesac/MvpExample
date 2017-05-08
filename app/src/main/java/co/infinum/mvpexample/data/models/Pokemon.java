package co.infinum.mvpexample.data.models;

import com.squareup.moshi.Json;

import android.text.TextUtils;

import java.io.Serializable;

public class Pokemon implements Serializable {

    private static final String NAME = "name";

    private static final String RESOURCE_URI = "resource_uri";

    private static final String HP = "hp";

    private static final String ATTACK = "attack";

    private static final String DEFENSE = "defense";

    private static final String HEIGHT = "height";

    private static final String WEIGHT = "weight";

    @Json(name = NAME)
    private String name;

    @Json(name = RESOURCE_URI)
    private String resourceUri;

    @Json(name = HP)
    private int hp;

    @Json(name = ATTACK)
    private int attack;

    @Json(name = DEFENSE)
    private int defense;

    @Json(name = HEIGHT)
    private String height;

    @Json(name = WEIGHT)
    private String weight;

    public int getId() {
        try {
            String[] tokens = TextUtils.split(resourceUri, "/");
            String id = TextUtils.isEmpty(tokens[tokens.length - 1]) ? tokens[tokens.length - 2] : tokens[tokens.length - 1];
            return Integer.parseInt(id);
        } catch (Exception e) {
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getHeight() {
        String heightStr = height;
        try {
            double centimeters;
            if (height.contains("'")) {
                String[] parts = height.split("'");
                centimeters = Double.parseDouble(parts[0]) / 0.032808;
                centimeters += Double.parseDouble(parts[1]) * 2.54;
            } else {
                centimeters = Double.parseDouble(height) / 0.032808;
            }
            heightStr = String.valueOf((int) centimeters) + " cm";
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return heightStr;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        String weightStr = weight;
        try {
            double lbs;
            if (weight.contains("lbs")) {
                lbs = Double.parseDouble(weight.substring(0, weight.indexOf('l') - 1));
            } else {
                lbs = Double.parseDouble(weight);
            }
            int kg = (int) (lbs * 0.45359237);
            weightStr = String.valueOf(kg) + " kg";
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return weightStr;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pokemon pokemon = (Pokemon) o;

        if (hp != pokemon.hp) {
            return false;
        }
        if (attack != pokemon.attack) {
            return false;
        }
        if (defense != pokemon.defense) {
            return false;
        }
        if (name != null ? !name.equals(pokemon.name) : pokemon.name != null) {
            return false;
        }
        if (resourceUri != null ? !resourceUri.equals(pokemon.resourceUri) : pokemon.resourceUri != null) {
            return false;
        }
        if (height != null ? !height.equals(pokemon.height) : pokemon.height != null) {
            return false;
        }
        return weight != null ? weight.equals(pokemon.weight) : pokemon.weight == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (resourceUri != null ? resourceUri.hashCode() : 0);
        result = 31 * result + hp;
        result = 31 * result + attack;
        result = 31 * result + defense;
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}
