package com.aster.justbuildmykeep.util;

import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class MathCalculator {
    public Vector3d computeLocationByDirection(Vector3d pos, Direction direction){
        switch(direction) {
            case DOWN:
                return rotateVector3d(pos,90,0,0);
            case UP:
                return rotateVector3d(pos,-90,0,0);
            case SOUTH:
                return rotateVector3d(pos,0,180,0);
            case WEST:
                return new Vector3d(0,0.7,0.3);
            case EAST:
                return rotateVector3d(pos,0,-90,0);
            case NORTH:
            default:
                return pos;
        }
    }
    public Quaternion computeRotationByDirection(Quaternion rotation, Direction direction){
        switch(direction) {
            case DOWN:
                return new Quaternion(0f,0f,0f,false);
            case UP:
                return new Quaternion(0f,0f,0f,false);
            case SOUTH:
                return new Quaternion(0f,0f,0f,false);
            case WEST:
                return new Quaternion(3.2f,-1.6f,0.05f,false);
            case EAST:
                return  new Quaternion(0f,0f,0f,false);
            case NORTH:
            default:
                return rotation;
        }
    }

    private Vector3d rotateVector3d(Vector3d pos,float pitch, float yaw, float roll){
        pos=pos.subtract(0.4,0.5,0.5);
        pos=pos.rotatePitch(pitch).rotateYaw(yaw).rotateRoll(roll);
        pos=pos.add(0.4,0.5,0.5);
        return pos;
    }
}
