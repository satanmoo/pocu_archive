//----------------------AttackIntent.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class AttackIntent {
    private Unit owner;
    private IntVector2D targetArea;
    private HashMap<IntVector2D, Integer> attackedAreasAndDamage = new HashMap<>();

    public AttackIntent(Unit owner, IntVector2D targetArea) {
        this.owner = owner;
        this.targetArea = targetArea;

        calculateDamageInArea();
    }

    public Unit getOwner() {
        return this.owner;
    }

    public IntVector2D getTargetArea() {
        return this.targetArea;
    }

    public void setTargetArea(HashMap<IntVector2D, Integer> attackedAreasAndDamage) {
        this.attackedAreasAndDamage = attackedAreasAndDamage;
    }

    private void calculateDamageInArea() {
        int leftX = Math.max(this.targetArea.getX() - this.owner.getAreaOfEffect(), 0);
        int rightX = Math.min(this.targetArea.getX() + this.owner.getAreaOfEffect(), this.targetArea.getMaxX() - 1);
        int topY = Math.max(this.targetArea.getY() - this.owner.getAreaOfEffect(), 0);
        int bottomY = Math.min(this.targetArea.getY() + this.owner.getAreaOfEffect(), this.targetArea.getMaxY() - 1);

        ArrayList<Integer> rangeX = new ArrayList<>();
        ArrayList<Integer> rangeY = new ArrayList<>();

        while (leftX <= rightX) {
            rangeX.add(leftX);
            ++leftX;
        }
        while (topY <= bottomY) {
            rangeY.add(topY);
            ++topY;
        }

        for (int x : rangeX) {
            for (int y : rangeY) {
                double distanceFromAttackedArea = Math.max(Math.abs(x - this.targetArea.getX()), Math.abs(y - this.targetArea.getY()));
                int calculateDamage = (int) (((double) this.owner.getAp()) * (1.0 - distanceFromAttackedArea / (this.owner.getAreaOfEffect() + 1.0)));

                this.attackedAreasAndDamage.put(new IntVector2D(x, y), calculateDamage);
            }
        }
    }

    public void attackAreas(ArrayList<Unit> units) {
        for (Unit enemy : units) {
            if (enemy.equals(this.owner)
                    && !this.owner.getAttackableUnitTypes().contains(enemy.getUnitType())) {
                continue;
            }

            for (Entry<IntVector2D, Integer> areaAndDamage : this.attackedAreasAndDamage.entrySet()) {
                IntVector2D area = areaAndDamage.getKey();
                int damage = areaAndDamage.getValue();

                if (!enemy.equals(this.owner)
                        && this.owner.getAttackableUnitTypes().contains(enemy.getUnitType())
                        && enemy.getPosition().getX() == area.getX()
                        && enemy.getPosition().getY() == area.getY()) {
                    enemy.onAttacked(damage);
                }
            }
        }
    }
}


//----------------------Destroyer.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;
import java.util.HashMap;

public class Destroyer extends Unit implements IThinkable {

    private HashMap<IntVector2D, Integer> bombingAllAreas = new HashMap<>();

    public Destroyer(final IntVector2D intVector2D) {
        super(intVector2D);
        this.hp = 999;
        this.ap = 999;
        this.vision = 0;
        this.areaOfEffect = 0;
        this.unitType = UnitType.AIR;

        this.attackableUnitTypes.add(UnitType.AIR);
        this.attackableUnitTypes.add(UnitType.GROUND);

        this.pointOfAttack = new int[][]{
                {0, -2}, {1, -2},
                {2, -1}, {2, 0}, {2, 1},
                {1, 2}, {0, 2}, {-1, 2},
                {-2, 1}, {-2, 0}, {-2, -1},
                {-1, -2}};

        for (int i = 0; i < 16 * 8; ++i) {
            this.bombingAllAreas.put(new IntVector2D(i % 16, i / 16), this.ap);
        }
    }

    @Override
    public IntentType think(ArrayList<Unit> units) {
        this.attackIntent = null;

        if (units.size() == 1) {
            return IntentType.NONE;
        }

        // 전부 폭격 시작
        this.attackIntent = new AttackIntent(this, new IntVector2D(0, 0));
        this.attackIntent.setTargetArea(this.bombingAllAreas);
        return IntentType.ATTACK;
    }

    @Override
    public void onAttacked(int damage) {
        this.hp -= 1;
        this.hp = (this.hp > 0 ? this.hp : 0);
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
    }

    @Override
    public char getSymbol() {
        return 'D';
    }
}


//----------------------ICollisionEventListener.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public interface ICollisionEventListener {
    boolean collsionEvent(Unit unit);
}


//----------------------IMovable.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public interface IMovable {
    void move();
}


//----------------------IntentType.java----------------------

package academy.pocu.comp2500.assignment3;

public enum IntentType {
    ATTACK,
    MOVE,
    NONE
}


//----------------------IntVector2D.java----------------------

package academy.pocu.comp2500.assignment3;

public class IntVector2D {
    private static final int MAX_X = 16;
    private static final int MAX_Y = 8;
    private int x;
    private int y;

    public IntVector2D(int x, int y) {
        // (0, 0) : 맵의 좌상단
        this.x = x;
        this.y = y;
    }

    public int getMaxX() {
        return MAX_X;
    }

    public int getMaxY() {
        return MAX_Y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int lengthToIntVector2D(IntVector2D intVector2D) {
        return (Math.abs(this.x - intVector2D.x) + Math.abs(this.y - intVector2D.y));
    }
}


//----------------------IThinkable.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public interface IThinkable {
    IntentType think(ArrayList<Unit> units);
}


//----------------------Marine.java----------------------

package academy.pocu.comp2500.assignment3;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Marine extends Unit implements IThinkable, IMovable {

    public Marine(final IntVector2D intVector2D) {
        super(intVector2D);
        this.hp = 35;
        this.ap = 6;
        this.vision = 2;
        this.areaOfEffect = 0;
        this.unitType = UnitType.GROUND;

        this.attackableUnitTypes.add(UnitType.AIR);
        this.attackableUnitTypes.add(UnitType.GROUND);

        this.pointOfAttack = new int[][]{{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    }

    @Override
    public IntentType think(ArrayList<Unit> units) {
        detectUnits(units);
        updateAttackableTiles();

        this.attackIntent = null;
        this.nextMovePosition = null;

        // 탐지된게 아무것도 없을 경우,
        if (this.detectedUnits.size() == 0) {
            // 없으면 아무것도 안함
            return IntentType.NONE;
        }

        // 탐지 됨.
        // 공격할 수 있는 타일에 적이 있는 지 여부.
        ArrayList<Unit> attackableEnemies = getAttackableEnemies();

        if (attackableEnemies.size() == 1) {
            this.attackIntent = new AttackIntent(this, attackableEnemies.get(0).getPosition());
            return IntentType.ATTACK;
        } else if (attackableEnemies.size() > 1) {
            this.attackIntent = createAttackIntent(attackableEnemies);
            return IntentType.ATTACK;
        }

        // if (attackableEnemies.size() == 0);
        // 없으면 이동 가능 여부.
        this.nextMovePosition = decideMoveArea();
        return IntentType.MOVE;
    }

    private IntVector2D decideMoveArea() {
        int thisX = this.intVector2D.getX();
        int thisY = this.intVector2D.getY();

        ArrayList<Unit> priorityUnits = this.detectedUnits;
        if (priorityUnits.size() == 1) {
            return targetingMovePriorityDirection(priorityUnits.get(0));
        }

        priorityUnits = compareToShortDistance(this.detectedUnits);
        if (priorityUnits.size() == 1) {
            return targetingMovePriorityDirection(priorityUnits.get(0));
        }

        priorityUnits = compareToMinHp(priorityUnits);
        if (priorityUnits.size() == 1) {
            return targetingMovePriorityDirection(priorityUnits.get(0));
        }

        // 8개 구역 확인
        for (Unit compareUnit : priorityUnits) {
            if (thisY > compareUnit.intVector2D.getY()
                    && thisX == compareUnit.intVector2D.getX()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisY > compareUnit.intVector2D.getY()
                    && thisX < compareUnit.intVector2D.getX()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisX < compareUnit.intVector2D.getX()
                    && thisY == compareUnit.intVector2D.getY()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisX < compareUnit.intVector2D.getX()
                    && thisY < compareUnit.intVector2D.getY()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisY < compareUnit.intVector2D.getY()
                    && thisX == compareUnit.intVector2D.getX()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisY < compareUnit.intVector2D.getY()
                    && thisX > compareUnit.intVector2D.getX()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisX > compareUnit.intVector2D.getX()
                    && thisY == compareUnit.intVector2D.getY()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisX > compareUnit.intVector2D.getX()
                    && thisY > compareUnit.intVector2D.getY()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        assert (false) : "Impossiable Target Unit Position";
        return new IntVector2D(thisX, thisY);
    }

    @Override
    public void move() {
        this.intVector2D.setX(this.nextMovePosition.getX());
        this.intVector2D.setY(this.nextMovePosition.getY());
    }

    @Override
    public void onAttacked(int damage) {
        this.hp -= damage;
        this.hp = (this.hp > 0 ? this.hp : 0);
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
    }

    @Override
    public char getSymbol() {
        return 'M';
    }
}


//----------------------Mine.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Mine extends Unit implements ICollisionEventListener {
    protected int stepCount;
    protected final int triggerStepCount;

    public Mine(final IntVector2D intVector2D, final int triggerStepCount) {
        super(intVector2D);
        this.hp = 1;
        this.ap = 10;
        this.vision = 0;
        this.areaOfEffect = 0;
        this.unitType = UnitType.GROUND;

        this.attackableUnitTypes.add(UnitType.GROUND);

        this.triggerStepCount = triggerStepCount;
        this.invisible = true;
    }

    @Override
    public void onAttacked(int damage) {
        this.hp -= damage;
        this.hp = (this.hp > 0 ? this.hp : 0);
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerCollisionEventListener(this);
    }

    @Override
    public char getSymbol() {
        return 'N';
    }

    @Override
    public boolean collsionEvent(Unit unit) {
        if (this.equals(unit)
                || unit.unitType.equals(UnitType.AIR)) {
            return false;
        }

        int thisX = this.intVector2D.getX();
        int thisY = this.intVector2D.getY();

        int otherX = unit.intVector2D.getX();
        int otherY = unit.intVector2D.getY();

        if (thisX != otherX
                || thisY != otherY) {
            return false;
        }

        ++this.stepCount;

        if (this.stepCount < this.triggerStepCount) {
            return false;
        }

        this.hp = 0;
        this.attackIntent = new AttackIntent(this, this.intVector2D);
        return true;
    }
}


//----------------------SimulationManager.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class SimulationManager {
    private static SimulationManager instance;
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<IThinkable> thinkableUnits = new ArrayList<>();
    private ArrayList<IMovable> movableUnits = new ArrayList<>();
    private ArrayList<ICollisionEventListener> collisionEventListenerUnits = new ArrayList<>();

    private SimulationManager() {
    }

    private void removeDeadUnits() {
        ArrayList<Unit> removeUnits = new ArrayList<>();

        for (Unit unit : this.units) {
            if (!unit.isLive()) {
                removeUnits.add(unit);
            }
        }
        if (removeUnits.size() != 0) {
            for (Unit removeUnit : removeUnits) {
                if (this.units.contains(removeUnit)) {
                    this.units.remove(removeUnit);
                }
            }
        }

        ArrayList<IThinkable> removeThinkableUnits = new ArrayList<>();

        for (IThinkable thinkable : this.thinkableUnits) {
            if (!((Unit) thinkable).isLive()) {
                removeThinkableUnits.add(thinkable);
            }
        }
        if (removeThinkableUnits.size() != 0) {
            for (IThinkable removeThinkableUnit : removeThinkableUnits) {
                if (this.thinkableUnits.contains(removeThinkableUnit)) {
                    this.thinkableUnits.remove(removeThinkableUnit);
                }
            }
        }

        ArrayList<ICollisionEventListener> removeCollisionEventListenerUnits = new ArrayList<>();

        for (ICollisionEventListener collisionEventListener : this.collisionEventListenerUnits) {
            if (!((Unit) collisionEventListener).isLive()) {
                removeCollisionEventListenerUnits.add(collisionEventListener);
            }
        }
        if (removeCollisionEventListenerUnits.size() != 0) {
            for (ICollisionEventListener removeCollisionEventListenerUnit : removeCollisionEventListenerUnits) {
                if (this.collisionEventListenerUnits.contains(removeCollisionEventListenerUnit)) {
                    this.collisionEventListenerUnits.remove(removeCollisionEventListenerUnit);
                }
            }
        }
    }

    // 시그내처 변경 X
    public static SimulationManager getInstance() {
        if (instance == null) {
            return (instance = new SimulationManager());
        }
        return instance;
    }

    public ArrayList<Unit> getUnits() {
        if (this.units != null) {
            removeDeadUnits();
        }
        return this.units;
    }

    public void spawn(Unit unit) {
        unit.onSpawn();
        this.units.add(unit);
    }

    // 다음 메서드의 매개변수 자료형은 변경 가능(아래로 3개만)
    public void registerThinkable(IThinkable thinkable) {
        this.thinkableUnits.add(thinkable);
    }

    public void registerMovable(IMovable movable) {
        this.movableUnits.add(movable);
    }

    public void registerCollisionEventListener(ICollisionEventListener listener) {
        this.collisionEventListenerUnits.add(listener);
    }

    public void update() {
        ArrayList<Unit> attackUnits = new ArrayList<>();

        // 각 유닛들이 이번 프레임에서 할 행동(선택지: 공격, 이동, 아무것도 안 함)을 결정
        // 생각할 수 있는 유닛들?
        for (IThinkable thinkable : this.thinkableUnits) {
            IntentType intentType = thinkable.think(this.units);

            switch (intentType) {
                case ATTACK:
                    attackUnits.add((Unit) thinkable);
                    break;

                case MOVE:
                    registerMovable((IMovable) thinkable);
                    break;

                case NONE:
                    break;

                default:
                    assert (false) : "Impossiable IntentType";
                    break;
            }
        }

        // 움직일 수 있는 각 유닛에게 이동할 기회를 줌
        for (IMovable movable : this.movableUnits) {
            movable.move();
        }

        // 이동 후 충돌 처리
        for (ICollisionEventListener collisionEventListener : this.collisionEventListenerUnits) {
            for (Unit unit : this.units) {
                boolean isCollisionEvent = collisionEventListener.collsionEvent(unit);
                if (isCollisionEvent == true
                        && !attackUnits.contains((Unit) collisionEventListener)) {
                    attackUnits.add((Unit) collisionEventListener);
                }
            }
        }

        // 각 유닛에게 공격할 기회를 줌
        // 피해를 입어야 하는 각 유닛에게 피해를 입힘
        for (Unit attacker : attackUnits) {
            AttackIntent attackIntent = attacker.attack();
            attackIntent.attackAreas(this.units);
        }

        // 죽은 유닛들을 모두 게임에서 제거함
        removeDeadUnits();

        // 계속 변경됨
        this.movableUnits.clear();
    }
}


//----------------------SmartMine.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class SmartMine extends Mine implements IThinkable {
    private int detectedEnemyNumber;

    public SmartMine(final IntVector2D intVector2D, final int triggerStepCount, final int detectedEnemyNumber) {
        super(intVector2D, triggerStepCount);
        this.ap = 15;
        this.vision = 1;
        this.areaOfEffect = 1;

        this.detectedEnemyNumber = detectedEnemyNumber;

        this.pointOfAttack = new int[][]{{0, 0}};
    }

    @Override
    public IntentType think(ArrayList<Unit> units) {
        detectUnits(units);
        updateAttackableTiles();

        this.attackIntent = null;

        if (this.detectedUnits.size() >= this.detectedEnemyNumber) {
            this.attackIntent = new AttackIntent(this, this.getPosition());
            this.hp = 0;
            return IntentType.ATTACK;
        }

        return IntentType.NONE;
    }

    @Override
    public void onAttacked(int damage) {
        this.hp -= damage;
        this.hp = (this.hp > 0 ? this.hp : 0);
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
        SimulationManager.getInstance().registerCollisionEventListener(this);
    }

    @Override
    public char getSymbol() {
        return 'A';
    }

}


//----------------------Tank.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Tank extends Unit implements IThinkable, IMovable {
    private boolean siegeMode = false;
    private boolean forward = true;

    public Tank(final IntVector2D intVector2D) {
        super(intVector2D);
        this.hp = 85;
        this.ap = 8;
        this.vision = 3;
        this.areaOfEffect = 1;
        this.unitType = UnitType.GROUND;

        this.attackableUnitTypes.add(UnitType.GROUND);

        this.pointOfAttack = new int[][]{
                {0, -2}, {1, -2},
                {2, -1}, {2, 0}, {2, 1},
                {1, 2}, {0, 2}, {-1, 2},
                {-2, 1}, {-2, 0}, {-2, -1},
                {-1, -2}};
    }

    @Override
    public IntentType think(ArrayList<Unit> units) {
        detectUnits(units);
        updateAttackableTiles();

        this.attackIntent = null;
        this.nextMovePosition = null;

        // 탐지된 게 아무것도 없을 경우,
        if (this.detectedUnits.size() == 0) {
            if (this.siegeMode == true) {
                this.siegeMode = false;
                return IntentType.NONE;
            }
            // 없으면 이동 가능 여부.
            this.nextMovePosition = decideMoveArea();
            return IntentType.MOVE;
        }

        // 탐지 됨.
        // 1. 현재 공성 모드가 아닌 경우 공성 모드로 변경
        if (this.siegeMode == false) {
            this.siegeMode = true;
            return IntentType.NONE;
        }

        ArrayList<Unit> attackableEnemies = getAttackableEnemies();
        if (attackableEnemies.size() == 1) {
            this.attackIntent = new AttackIntent(this, attackableEnemies.get(0).getPosition());
            return IntentType.ATTACK;
        } else if (attackableEnemies.size() > 1) {
            this.attackIntent = createAttackIntent(attackableEnemies);
            return IntentType.ATTACK;
        }

        return IntentType.NONE;
    }

    private IntVector2D decideMoveArea() {
        if (this.siegeMode == true) {
            return this.intVector2D;
        }
        int thisX = this.intVector2D.getX();

        if (thisX == this.intVector2D.getMaxX() - 1) {
            this.forward = false;
        } else if (thisX == 0) {
            this.forward = true;
        }

        thisX += (forward == true ? 1 : -1);

        return new IntVector2D(thisX, this.intVector2D.getY());
    }

    public void move() {
        this.intVector2D.setX(this.nextMovePosition.getX());
        this.intVector2D.setY(this.nextMovePosition.getY());
    }

    @Override
    public void onAttacked(int damage) {
        this.hp -= (this.siegeMode == true ? damage * 2 : damage);
        this.hp = (this.hp > 0 ? this.hp : 0);
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
    }

    @Override
    public char getSymbol() {
        return 'T';
    }
}


//----------------------Turret.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Turret extends Unit implements IThinkable {
    public Turret(final IntVector2D intVector2D) {
        super(intVector2D);
        this.hp = 99;
        this.ap = 7;
        this.vision = 2;
        this.areaOfEffect = 0;
        this.unitType = UnitType.GROUND;

        this.attackableUnitTypes.add(UnitType.AIR);

        this.pointOfAttack = new int[][]{{0, 0}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
    }

    @Override
    public IntentType think(ArrayList<Unit> units) {
        detectUnits(units);
        updateAttackableTiles();

        this.attackIntent = null;

        // 탐지된게 아무것도 없을 경우,
        if (this.detectedUnits.size() == 0) {
            return IntentType.NONE;
        }

        // 탐지 됨.
        // 공격할 수 있는 타일에 적이 있는 지 여부.
        ArrayList<Unit> attackableEnemies = getAttackableEnemies();

        if (attackableEnemies.size() == 1) {
            this.attackIntent = new AttackIntent(this, attackableEnemies.get(0).getPosition());
            return IntentType.ATTACK;
        } else if (attackableEnemies.size() > 1) {
            this.attackIntent = createAttackIntent(attackableEnemies);
            return IntentType.ATTACK;
        }

        // if (attackableEnemies.size() == 0)
        return IntentType.NONE;
    }

    @Override
    public void onAttacked(int damage) {
        this.hp -= damage;
        this.hp = (this.hp > 0 ? this.hp : 0);
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
    }

    @Override
    public char getSymbol() {
        return 'U';
    }
}


//----------------------Unit.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public abstract class Unit {
    protected IntVector2D intVector2D;
    protected AttackIntent attackIntent;
    protected UnitType unitType;
    protected ArrayList<UnitType> attackableUnitTypes = new ArrayList<>();
    protected ArrayList<IntVector2D> attackableTiles = new ArrayList<>();
    protected int[][] pointOfAttack;
    protected ArrayList<Unit> detectedUnits = new ArrayList<>();
    protected IntVector2D nextMovePosition;
    protected int vision;
    protected int areaOfEffect;
    protected int ap;
    protected int hp;
    protected boolean invisible = false;

    protected Unit(final IntVector2D intVector2D) {
        this.intVector2D = intVector2D;
    }

    public int getAreaOfEffect() {
        return this.areaOfEffect;
    }

    public int getVision() {
        return this.vision;
    }

    public int getAp() {
        return this.ap;
    }

    public UnitType getUnitType() {
        return this.unitType;
    }

    public ArrayList<UnitType> getAttackableUnitTypes() {
        return this.attackableUnitTypes;
    }

    public boolean isLive() {
        return (this.hp > 0);
    }

    protected void detectUnits(ArrayList<Unit> units) {
        this.detectedUnits.clear();

        int leftX = Math.max(this.intVector2D.getX() - this.vision, 0);
        int rightX = Math.min(this.intVector2D.getX() + this.vision, this.intVector2D.getMaxX() - 1);
        int topY = Math.max(this.intVector2D.getY() - this.vision, 0);
        int bottomY = Math.min(this.intVector2D.getY() + this.vision, this.intVector2D.getMaxY() - 1);

        ArrayList<Integer> rangeX = new ArrayList<>();
        ArrayList<Integer> rangeY = new ArrayList<>();

        while (leftX <= rightX) {
            rangeX.add(leftX);
            ++leftX;
        }
        while (topY <= bottomY) {
            rangeY.add(topY);
            ++topY;
        }

        for (Unit enemyUnit : units) {
            if (!this.equals(enemyUnit)
                    && enemyUnit.invisible == false
                    && this.attackableUnitTypes.contains(enemyUnit.unitType)
                    && rangeX.contains(enemyUnit.intVector2D.getX())
                    && rangeY.contains(enemyUnit.intVector2D.getY())) {
                this.detectedUnits.add(enemyUnit);
            }
        }
    }

    protected void updateAttackableTiles() {
        this.attackableTiles.clear();

        int x = this.intVector2D.getX();
        int y = this.intVector2D.getY();
        int maxX = this.intVector2D.getMaxX();
        int maxY = this.intVector2D.getMaxY();

        int attableX;
        int attableY;

        for (int i = 0; i < this.pointOfAttack.length; ++i) {
            attableX = x;
            attableY = y;

            attableX += this.pointOfAttack[i][0];
            attableY += this.pointOfAttack[i][1];

            if (attableX < 0
                    || attableY < 0
                    || attableX > maxX - 1
                    || attableY > maxY - 1) {
                continue;
            }
            this.attackableTiles.add(new IntVector2D(attableX, attableY));
        }
    }

    protected ArrayList<Unit> getAttackableEnemies() {
        ArrayList<Unit> targetUnits = new ArrayList<>();

        for (Unit detectedUnit : this.detectedUnits) {
            for (IntVector2D attackableTile : this.attackableTiles) {
                if (attackableTile.getX() == detectedUnit.getPosition().getX()
                        && attackableTile.getY() == detectedUnit.getPosition().getY()) {
                    targetUnits.add(detectedUnit);
                }
            }
        }
        return targetUnits;
    }

    protected AttackIntent createAttackIntent(ArrayList<Unit> attackableEnemys) {
        ArrayList<Unit> attackableUnits = compareToMinHp(attackableEnemys);

        if (attackableUnits.size() > 1) {
            for (IntVector2D attackableTile : this.attackableTiles) {
                for (Unit targetUnit : attackableUnits) {
                    if (targetUnit.getPosition().getX() == attackableTile.getX() &&
                            targetUnit.getPosition().getY() == attackableTile.getY()) {
                        return new AttackIntent(this, targetUnit.getPosition());
                    }
                }
            }
            assert (false) : "Impossiable Logic";
        }

        return new AttackIntent(this, attackableUnits.get(0).getPosition());
    }


    protected ArrayList<Unit> compareToShortDistance(ArrayList<Unit> compareUnits) {
        int minDistance = 16 * 8;
        ArrayList<Unit> priorityUnits = new ArrayList<>();
        int distance;

        for (Unit compareUnit : compareUnits) {
            distance = this.intVector2D.lengthToIntVector2D(compareUnit.intVector2D);
            if (minDistance > distance) {
                minDistance = distance;
            }
        }

        for (Unit compareUnit : compareUnits) {
            distance = this.intVector2D.lengthToIntVector2D(compareUnit.intVector2D);
            if (minDistance == distance) {
                priorityUnits.add(compareUnit);
            }
        }
        return priorityUnits;
    }

    protected IntVector2D targetingMovePriorityDirection(Unit targetUnit) {
        int thisX = this.intVector2D.getX();
        int thisY = this.intVector2D.getY();
        int targetUnitX = targetUnit.getPosition().getX();
        int targetUnitY = targetUnit.getPosition().getY();

        if (thisY != targetUnitY) {
            thisY = (thisY > targetUnitY ? thisY - 1 : thisY + 1);
        } else if (thisX != targetUnitX) {
            thisX = (thisX > targetUnitX ? thisX - 1 : thisX + 1);
        }
        return new IntVector2D(thisX, thisY);
    }

    protected ArrayList<Unit> compareToMinHp(ArrayList<Unit> compareUnits) {
        int minHp = 10000;
        ArrayList<Unit> priorityUnits = new ArrayList<>();

        for (Unit compareUnit : compareUnits) {
            if (minHp > compareUnit.hp) {
                minHp = compareUnit.hp;
            }
        }

        for (Unit compareUnit : compareUnits) {
            if (minHp == compareUnit.hp) {
                priorityUnits.add(compareUnit);
            }
        }
        return priorityUnits;
    }

    // 시그내처 변경 X
    public IntVector2D getPosition() {
        return this.intVector2D;
    }

    public int getHp() {
        return this.hp;
    }

    public AttackIntent attack() {
        return this.attackIntent;
    }

    public abstract void onAttacked(int damage);

    public abstract void onSpawn();

    public abstract char getSymbol();
}


//----------------------UnitType.java----------------------

package academy.pocu.comp2500.assignment3;

public enum UnitType {
    GROUND,
    AIR
}


//----------------------Wraith.java----------------------

package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Wraith extends Unit implements IThinkable, IMovable {
    private IntVector2D firstPosition = new IntVector2D(this.intVector2D.getX(), this.intVector2D.getY());
    private int shieldCount = 1;
    private boolean onShield = false;

    public Wraith(final IntVector2D intVector2D) {
        super(intVector2D);
        this.hp = 80;
        this.ap = 6;
        this.vision = 4;
        this.areaOfEffect = 0;
        this.unitType = UnitType.AIR;

        this.attackableUnitTypes.add(UnitType.AIR);
        this.attackableUnitTypes.add(UnitType.GROUND);

        this.pointOfAttack = new int[][]{{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    }

    @Override
    public IntentType think(ArrayList<Unit> units) {
        detectUnits(units);
        updateAttackableTiles();

        this.attackIntent = null;
        this.nextMovePosition = null;
        this.onShield = false;

        // 탐지된게 아무것도 없을 경우,
        if (this.detectedUnits.size() == 0) {
            this.nextMovePosition = decideMoveArea();
            return IntentType.MOVE;
        }

        // 탐지 됨.
        // 공격할 수 있는 타일에 적이 있는 지 여부.
        ArrayList<Unit> attackableEnemies = getAttackableEnemies();
        ArrayList<Unit> airTypeEnemies = new ArrayList<>();
        ArrayList<Unit> groundTypeEnemies = new ArrayList<>();

        if (attackableEnemies.size() == 1) {
            this.attackIntent = new AttackIntent(this, attackableEnemies.get(0).getPosition());
            return IntentType.ATTACK;
        } else if (attackableEnemies.size() > 1) {
            // Unit Type : Air, Ground
            for (Unit otherTypeUnit : attackableEnemies) {
                if (otherTypeUnit.unitType.equals(UnitType.AIR)) {
                    airTypeEnemies.add(otherTypeUnit);
                } else {
                    groundTypeEnemies.add(otherTypeUnit);
                }
            }

            if (airTypeEnemies.size() == 0) {
                this.attackIntent = createAttackIntent(groundTypeEnemies);
            } else {
                this.attackIntent = createAttackIntent(airTypeEnemies);
            }
            return IntentType.ATTACK;
        }

        this.nextMovePosition = decideMoveArea();
        return IntentType.MOVE;
    }

    private IntVector2D decideMoveArea() {
        int thisX = this.intVector2D.getX();
        int thisY = this.intVector2D.getY();

        // 시야에 적 없을 시,
        if (this.detectedUnits.size() == 0) {
            int targetX = this.firstPosition.getX();
            int targetY = this.firstPosition.getY();

            if (thisY != targetY) {
                thisY = (thisY > targetY ? thisY - 1 : thisY + 1);
            } else if (thisX != targetX) {
                thisX = (thisX > targetX ? thisX - 1 : thisX + 1);
            }
            return new IntVector2D(thisX, thisY);
        }

        ArrayList<Unit> airTypeEnemies = new ArrayList<>();
        ArrayList<Unit> groundTypeEnemies = new ArrayList<>();

        // Unit Type : Air, Ground
        for (Unit otherTypeUnit : this.detectedUnits) {
            if (otherTypeUnit.unitType.equals(UnitType.AIR)) {
                airTypeEnemies.add(otherTypeUnit);
            } else {
                groundTypeEnemies.add(otherTypeUnit);
            }
        }

        if (airTypeEnemies.size() != 0) {
            return priorityMovePosition(airTypeEnemies);
        }
        return priorityMovePosition(groundTypeEnemies);
    }

    @Override
    public void move() {
        this.intVector2D.setX(this.nextMovePosition.getX());
        this.intVector2D.setY(this.nextMovePosition.getY());
    }

    private IntVector2D priorityMovePosition(ArrayList<Unit> detectedUnits) {
        int thisX = this.intVector2D.getX();
        int thisY = this.intVector2D.getY();

        ArrayList<Unit> priorityUnits = detectedUnits;
        if (priorityUnits.size() == 1) {
            return targetingMovePriorityDirection(priorityUnits.get(0));
        }

        priorityUnits = compareToShortDistance(detectedUnits);
        if (priorityUnits.size() == 1) {
            return targetingMovePriorityDirection(priorityUnits.get(0));
        }

        priorityUnits = compareToMinHp(priorityUnits);
        if (priorityUnits.size() == 1) {
            return targetingMovePriorityDirection(priorityUnits.get(0));
        }

        // 8개 구역 확인
        for (Unit compareUnit : priorityUnits) {
            if (thisY > compareUnit.intVector2D.getY()
                    && thisX == compareUnit.intVector2D.getX()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisY > compareUnit.intVector2D.getY()
                    && thisX < compareUnit.intVector2D.getX()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisX < compareUnit.intVector2D.getX()
                    && thisY == compareUnit.intVector2D.getY()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisX < compareUnit.intVector2D.getX()
                    && thisY < compareUnit.intVector2D.getY()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisY < compareUnit.intVector2D.getY()
                    && thisX == compareUnit.intVector2D.getX()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisY < compareUnit.intVector2D.getY()
                    && thisX > compareUnit.intVector2D.getX()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisX > compareUnit.intVector2D.getX()
                    && thisY == compareUnit.intVector2D.getY()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        for (Unit compareUnit : priorityUnits) {
            if (thisX > compareUnit.intVector2D.getX()
                    && thisY > compareUnit.intVector2D.getY()) {
                return targetingMovePriorityDirection(compareUnit);
            }
        }

        assert (false) : "Impossiable Target Unit Position";
        return new IntVector2D(thisX, thisY);
    }

    @Override
    public void onAttacked(int damage) {
        if (onShield == true) {
            return;
        }

        if (onShield == false && shieldCount > 0) {
            onShield = true;
            --shieldCount;
            return;
        }

        this.hp -= damage;
        this.hp = (this.hp > 0 ? this.hp : 0);
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
    }

    @Override
    public char getSymbol() {
        return 'W';
    }
}