# Week3

## Limitation of Constructor

![img.png](image/img.png)
![img_1.png](image/img_1.png)
![img_2.png](image/img_2.png)
![img_3.png](image/img_3.png)
![img_4.png](image/img_4.png)

- Object should be responsible for own state
    - Constructor alone can't accomplish this

## Access Modifier

![img_5.png](image/img_5.png)
![img_6.png](image/img_6.png)

### Access Modifier: Public

![img_7.png](image/img_7.png)

- In java, a class can be declared with either public or default (package-private) access modifier.
    - If no access modifier is specified, the class is given default access, meaning it is only accessible within the
      same package.
    - `public` class can be accessed from any other class, regardless of the package.
        - The `public` access modifier must be explicitly stated, whereas the default access is implied when no modifier
          is provided.

#### reference

- https://docs.oracle.com/javase/specs/jls/se11/html/jls-6.html#jls-6.6

> If a top level class or interface type is declared public and is a member of a package that is not exported by a
> module, then the type may be accessed by any code in the same module.
> If a top level class or interface type is declared with package access, then it may be accessed only from within the
> package in which it is declared.
> A top level class or interface type declared without an access modifier implicitly has package access.

### Access Modifier: Private

![img_8.png](image/img_8.png)

- In java, the `private` access modifier can only be applied to nested classes.
- For top-level classes, only the `public` access modifier or no access modifier(default access) can be used.

![img_9.png](image/img_9.png)
![img_10.png](image/img_10.png)
![img_11.png](image/img_11.png)

- Private method inside a public method can be called indirectly by calling a public method.

### Common Usage of Access Modifier

![img_12.png](image/img_12.png)

### Usage of Private Method

![img_13.png](image/img_13.png)
![img_14.png](image/img_14.png)
![img_15.png](image/img_15.png)
![img_16.png](image/img_16.png)
![img_17.png](image/img_17.png)
![img_18.png](image/img_18.png)
![img_19.png](image/img_19.png)

### Usage of Private Constructor

![img_20.png](image/img_20.png)

- when creating an object by `new` keyword, a compilation error occurs because of private constructor

### Summary of the private Access Modifier and Key Considerations

![img_21.png](image/img_21.png)
![img_22.png](image/img_22.png)

- ***Internal*** in java refers to the class level, not the instance level
- An instance of one class can access private member of another instance of the same class

## Access Modifier: Default

![img_23.png](image/img_23.png)
![img_24.png](image/img_24.png)
![img_25.png](image/img_25.png)

```java
// Application.java

happy.happiness +=1.0;     // compile error! Application.java exists in a different package.
```

![img_26.png](image/img_26.png)
![img_27.png](image/img_27.png)
![img_28.png](image/img_28.png)
![img_29.png](image/img_29.png)

- If you unavoidably split files in the same package, you can use a package access modifier.
- For example, if you have class a-1 and class a-2, you can put them both in one package and access members of class a-2
  from class a-1.
    - This is better for encapsulation than making the members of class a-2 public.
- Changing an implicit class to a top-level class is also a special case of splitting class file in the same package.

## Getter & Setter

![img_30.png](image/img_30.png)
![img_31.png](image/img_31.png)
![img_32.png](image/img_32.png)

- The getter concept involves abstracting data

![img_33.png](image/img_33.png)
![img_34.png](image/img_34.png)
![img_35.png](image/img_35.png)
![img_37.png](image/img_37.png)
![img_36.png](image/img_36.png)
![img_38.png](image/img_38.png)
![img_39.png](image/img_39.png)

## Setter Best Practice

![img_40.png](image/img_40.png)
![img_41.png](image/img_41.png)
![img_42.png](image/img_42.png)

- powerful ***encapsulation***

![img_43.png](image/img_43.png)
![img_44.png](image/img_44.png)
![img_45.png](image/img_45.png)

- member variable pet is reference and mutable, this can be problematic.

![img_46.png](image/img_46.png)
![img_47.png](image/img_47.png)

- member variable mean is updated each time setScore() is called. This is conceptually different from modifying the mean
  variable directly by calling setMean()

![img_48.png](image/img_48.png)

- Create setters only when needed.

## Encapsulation & Data Abstraction

![img_49.png](image/img_49.png)
![img_50.png](image/img_50.png)
![img_51.png](image/img_51.png)

- A private method is used to eliminate code duplication within a class.
- According to the principle of encapsulation, logic that is only used internally within the class does not need to be
  exposed to the outside.

![img_52.png](image/img_52.png)
![img_53.png](image/img_53.png)
![img_54.png](image/img_54.png)
![img_55.png](image/img_55.png)

## Object Modeling

![img_56.png](image/img_56.png)
![img_57.png](image/img_57.png)
![img_58.png](image/img_58.png)
![img_59.png](image/img_59.png)

## Class Diagram

![img_60.png](image/img_60.png)
![img_61.png](image/img_61.png)
![img_62.png](image/img_62.png)
![img_63.png](image/img_63.png)
![img_64.png](image/img_64.png)

- In the past, consultants secured contracts based on UML, and developers carried out their work using these models as a
  foundation

![img_65.png](image/img_65.png)
![img_66.png](image/img_66.png)
![img_67.png](image/img_67.png)

### Class Code Example

![img_68.png](image/img_68.png)
![img_69.png](image/img_69.png)
![img_70.png](image/img_70.png)

- Because UML does not represent implementations, so class code omits the implementations.

### Class Name

![img_71.png](image/img_71.png)

### Member Variable

![img_72.png](image/img_72.png)

- Conceptually refers to state
- it is represented that member variable happiness is initialized to 0.
    - name, age is initialized by constructor. their value is determined by the value of constructor's parameters.

### Member Function(Method)

![img_73.png](image/img_73.png)

### Access Modifier

![img_74.png](image/img_74.png)

### Dependency

![img_75.png](image/img_75.png)
![img_76.png](image/img_76.png)

## Object Modeling Example

![img_77.png](image/img_77.png)
![img_78.png](image/img_78.png)

### Step1: Waterspray modeling

![img_79.png](image/img_79.png)
![img_80.png](image/img_80.png)
![img_81.png](image/img_81.png)
![img_82.png](image/img_82.png)
![img_83.png](image/img_83.png)
![img_84.png](image/img_84.png)
![img_85.png](image/img_85.png)
![img_86.png](image/img_86.png)

### Step2: define behavior(watering)

![img_87.png](image/img_87.png)
![img_88.png](image/img_88.png)
![img_89.png](image/img_89.png)
![img_90.png](image/img_90.png)
![img_91.png](image/img_91.png)

#### Determine method signature

![img_92.png](image/img_92.png)
![img_93.png](image/img_93.png)
![img_94.png](image/img_94.png)
![img_95.png](image/img_95.png)
![img_96.png](image/img_96.png)

#### Think about whether the behavior is necessary

![img_97.png](image/img_97.png)
![img_98.png](image/img_98.png)
![img_99.png](image/img_99.png)
![img_100.png](image/img_100.png)
![img_101.png](image/img_101.png)
![img_102.png](image/img_102.png)
![img_103.png](image/img_103.png)
![img_104.png](image/img_104.png)

### Step3: Add a status(reflecting capacity of bottle)

![img_105.png](image/img_105.png)
![img_106.png](image/img_106.png)
![img_107.png](image/img_107.png)

#### Constants in UML

![img_108.png](image/img_108.png)
![img_109.png](image/img_109.png)
![img_110.png](image/img_110.png)
![img_111.png](image/img_111.png)
![img_112.png](image/img_112.png)
![img_113.png](image/img_113.png)
![img_114.png](image/img_114.png)

- Constant is not state

#### Member variable in UML

![img_115.png](image/img_115.png)
![img_116.png](image/img_116.png)
![img_117.png](image/img_117.png)

- Unlike constants, member variables are reflected in UML.

#### Additional behavior

![img_118.png](image/img_118.png)
![img_119.png](image/img_119.png)
![img_120.png](image/img_120.png)
![img_121.png](image/img_121.png)
![img_122.png](image/img_122.png)
![img_123.png](image/img_123.png)

- We can add status and behavior **when we need them**

### Step4: Think about additional object

![img_124.png](image/img_124.png)
![img_125.png](image/img_125.png)
![img_126.png](image/img_126.png)
![img_127.png](image/img_127.png)
![img_128.png](image/img_128.png)
![img_129.png](image/img_129.png)
![img_130.png](image/img_130.png)
![img_131.png](image/img_131.png)

- member variable `remainingWater` in class `WaterSpray` is private member variable.
    - because of ***Encapsulation***
- public member function `addWater` can be called anywhere. so anyone other than the `Faucet`class can
  modify `remaingWater`

![img_132.png](image/img_132.png)
![img_133.png](image/img_133.png)

#### Is this absolutely need?

![img_134.png](image/img_134.png)
![img_135.png](image/img_135.png)
![img_136.png](image/img_136.png)
![img_137.png](image/img_137.png)
![img_138.png](image/img_138.png)

- In conclusion, step4 is cancel.

### Step5: Add object(flowerpot)

#### status of flowerpot

![img_139.png](image/img_139.png)
![img_140.png](image/img_140.png)
![img_141.png](image/img_141.png)
![img_142.png](image/img_142.png)
![img_143.png](image/img_143.png)
![img_145.png](image/img_145.png)
![img_144.png](image/img_144.png)
![img_146.png](image/img_146.png)
![img_147.png](image/img_147.png)

#### behavior of flowerpot

![img_148.png](image/img_148.png)
![img_149.png](image/img_149.png)
![img_150.png](image/img_150.png)
![img_151.png](image/img_151.png)
![img_152.png](image/img_152.png)
![img_153.png](image/img_153.png)
![img_154.png](image/img_154.png)

#### Implementations that don't accurately reflect state

![img_155.png](image/img_155.png)
![img_156.png](image/img_156.png)
![img_157.png](image/img_157.png)
![img_158.png](image/img_158.png)
![img_159.png](image/img_159.png)
![img_160.png](image/img_160.png)
![img_161.png](image/img_161.png)
![img_162.png](image/img_162.png)
![img_163.png](image/img_163.png)
![img_164.png](image/img_164.png)
![img_165.png](image/img_165.png)
![img_166.png](image/img_166.png)
![img_167.png](image/img_167.png)

### Step6: Autonomy of Object in OOP

#### Method1

![img_168.png](image/img_168.png)
![img_169.png](image/img_169.png)
![img_170.png](image/img_170.png)
![img_171.png](image/img_171.png)
![img_172.png](image/img_172.png)

- Better encapsulation and data abstraction

#### Method2

![img_173.png](image/img_173.png)
![img_174.png](image/img_174.png)
![img_175.png](image/img_175.png)
![img_176.png](image/img_176.png)
![img_177.png](image/img_177.png)
![img_178.png](image/img_178.png)
![img_179.png](image/img_179.png)
![img_180.png](image/img_180.png)

- This is concept of encapsulation and object autonomy
- Objects act with ***autonomy***
    - So when translating to Korean, understand that "개체" are more appropriate than "객체"

![img_181.png](image/img_181.png)

- The parameter's type was changed from int to WaterSpray, dependency between two class gets stronger.

![img_182.png](image/img_182.png)
![img_183.png](image/img_183.png)

- less flexible

### Step7: separate object

![img_184.png](image/img_184.png)
![img_185.png](image/img_185.png)
![img_186.png](image/img_186.png)
![img_187.png](image/img_187.png)
![img_188.png](image/img_188.png)
![img_189.png](image/img_189.png)
![img_190.png](image/img_190.png)

- this is ***Aggregation*** not ***Composition***
    - instance of each class can be used independently

![img_191.png](image/img_191.png)
![img_192.png](image/img_192.png)
![img_193.png](image/img_193.png)
![img_194.png](image/img_194.png)
![img_195.png](image/img_195.png)
![img_196.png](image/img_196.png)
![img_197.png](image/img_197.png)
![img_198.png](image/img_198.png)

#### Flexible design is not the best!

![img_199.png](image/img_199.png)
![img_200.png](image/img_200.png)
![img_201.png](image/img_201.png)
![img_202.png](image/img_202.png)
![img_203.png](image/img_203.png)
![img_204.png](image/img_204.png)
![img_205.png](image/img_205.png)
![img_206.png](image/img_206.png)
![img_207.png](image/img_207.png)
![img_208.png](image/img_208.png)
![img_209.png](image/img_209.png)
![img_210.png](image/img_210.png)

#### OOP guideline

![img_211.png](image/img_211.png)
![img_212.png](image/img_212.png)

### Step8: Increase reusability correctly

#### Method1: Introduce the concept of standards

- problem: caller create head and body separately. this is complicated for user(caller) and it has poor readability

![img_213.png](image/img_213.png)
![img_214.png](image/img_214.png)
![img_215.png](image/img_215.png)
![img_216.png](image/img_216.png)

- Implementing standards as enum

![img_217.png](image/img_217.png)
![img_218.png](image/img_218.png)
![img_219.png](image/img_219.png)
![img_220.png](image/img_220.png)

- Make it easier for callers by creating a constructor that takes an enumeration as an argument

#### Method2: Integrating common logic

- problem: The FlowerPot.addWater() method logic is complex

![img_221.png](image/img_221.png)
![img_222.png](image/img_222.png)
![img_223.png](image/img_223.png)
![img_224.png](image/img_224.png)
![img_225.png](image/img_225.png)
![img_226.png](image/img_226.png)
![img_227.png](image/img_227.png)
