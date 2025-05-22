package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjBall extends SuperObject{

    public ObjBall(){

        name = "Ball";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/pokeball.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
