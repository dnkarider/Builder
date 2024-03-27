import java.util.OptionalInt;

public class Main {
    public class Person{
        public Person(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public Person(String name, String surname, int age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
        }

        public Person(String name, String surname, int age, String address) {
            this.name = name;
            this.surname = surname;
            this.age = age;
            this.address = address;
        }

        protected String name;
        protected String surname;
        protected int age;
        protected String address;
        public void happyBirthday(){
            age++;
        }
        public boolean hasAge(){
            return age != 0;
        }
        public boolean hasAddress(){
            return address != null;
        }
        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            if(hasAddress()){
                return address;
            }
            else{
                System.out.println("Address isnt found");
                return null;
            }}
        public OptionalInt getAge() throws NullPointerException{
            OptionalInt objInt = OptionalInt.of(age);
            if(objInt.getAsInt() != 0){
                return objInt;
            }
            else{
                return null;
            }
        }
        public String getName(){
            return name;
        }
        public String getSurname(){
            return surname;
        }
        @Override
        public String toString() throws NullPointerException {
            try{
                return STR."[name = \{getName()}, surname = \{getSurname()}, age = \{getAge().getAsInt()}, address = \{getAddress()}]";
            }
            catch (NullPointerException e){
                System.out.println("Возвраст не найден");
                return STR."[name = \{getName()}, surname = \{getSurname()}, address = \{getAddress()}]";
            }
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + age; result = prime * result + ((surname == null) ? 0 : surname.hashCode());
            return result;
        }
        public PersonBuilder newChildBuilder() {//используется для получения полузаполненного обьекта PersonBuilder с name и surname - младенец
            PersonBuilder personBuilder = new PersonBuilder();
            personBuilder.setName(name)
                    .setSurname(surname);
            return personBuilder;
        }
    }
    public interface IPersonBuilder{
        public PersonBuilder setName(String name);
        public PersonBuilder setSurname(String surname);
        public PersonBuilder setAge(int age);
        public PersonBuilder setAddress(String address);
        public Person build();
    }
    public class PersonBuilder implements IPersonBuilder {
        private String name;
        private String surname;
        private int age;
        private String address;
        @Override
        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }
        @Override
        public PersonBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }
        @Override
        public PersonBuilder setAge(int age) throws IllegalArgumentException {
            if (age < 0){
                throw new IllegalArgumentException();
            }
            this.age = age;
            return this;
        }
        @Override
        public PersonBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        @Override
        public Person build() throws IllegalStateException{
            if(name.equals(null) || surname.equals(null)){
                throw new IllegalStateException();
            }
            return new Person(name, surname, age, address);
        }
    }
    public void main(String[] args) throws NullPointerException, IllegalStateException, IllegalArgumentException{
        try {
            Person mom = new PersonBuilder()
                    .setName("Анна")
                    .setSurname("Вольф")
                    .setAge(31)
                    .setAddress("Сидней")
                    .build();
            Person son = mom.newChildBuilder()
                    .setName("Антошка")
                    .build();
            System.out.println("У " + mom + " есть сын, " + son);
        } catch(NullPointerException e){
            e.printStackTrace();
        }
        try{
            // Не хватает обязательных полей
            new PersonBuilder().build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            // Возраст недопустимый
            new PersonBuilder().setAge(-100).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}