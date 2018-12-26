// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.account;

import java.util.Random;

public class EmailAccountBuilder
    implements AccountBuilder
{

    private EmailAccountBuilder()
    {
        ran = new Random();
    }

    public static EmailAccountBuilder getInstance()
    {
        return BUILDER;
    }

    public Account random()
    {
        return new Account((new StringBuilder(String.valueOf(surname[ran.nextInt(surname.length)]))).append(ran.nextInt(0x1388066)).append("@").append(email[ran.nextInt(email.length)]).toString(), (new StringBuilder(String.valueOf(Integer.toHexString(9999 + ran.nextInt(10000))))).append(0x1869f + ran.nextInt(0x186a0)).toString());
    }

    private static final EmailAccountBuilder BUILDER = new EmailAccountBuilder();
    private String email[] = {
        "163.com", "126.com", "qq.com", "sina.com", "sohu.com", "21cn.com", "gmail.com", "hotmail.com", "tom.com", "sogou.com", 
        "263.com"
    };
    private Random ran;
    private String surname[] = {
        "Abigail", "Ada", "Adela", "Adelaide", "Afra", "Agatha", "Agnes", "Alberta", "Alexia", "Alice", 
        "Alma", "Althea", "Alva", "Amelia", "Amy", "Anastasia", "Andrea", "Ann", "Anna", "Annabelle", 
        "Antonia", "April", "Arabela", "Arlene", "Astrid", "Atalanta", "Athena", "Audrey", "Aurora", "Barbara", 
        "Beatrice", "Belinda", "Bella", "Belle", "Bernice", "Bertha", "Beryl", "Bess", "Betsy", "Betty", 
        "Beulah", "Beverly", "Blanche", "Bblythe", "Breenda", "Bridget", "Brook", "Camille", "Candance", "Candice", 
        "Cara", "Carol", "Caroline", "Catherine", "Cathy", "Cecilia", "Celeste", "Charlotte", "Cherry", "Cheryl", 
        "Chloe", "Christine", "Claire", "Clara", "Clementine", "Constance", "Cora", "Coral", "Cornelia", "Daisy", 
        "Dale", "Dana", "Daphne", "Darlene", "Dawn", "Debby", "Deborah", "Deirdre", "Delia", "Denise", 
        "Diana", "Dinah", "Dolores", "Dominic", "Donna", "Dora", "Doreen", "Doris", "Dorothy", "Eartha", 
        "Eden", "Edith", "Edwina", "Eileen", "Elaine", "Eleanore", "Elizabeth", "Ella", "Ellen", "Elma", 
        "Elsa", "Elsie", "Elva", "Elvira", "Emily", "Emma", "Erica", "Erin", "Esther", "Eudora", 
        "Eunice", "Evangeline", "Eve", "Evelyn", "Faithe", "Fanny", "Fay", "Flora", "Florence", "Frances", 
        "Freda", "Frederica", "Gabrielle", "Gail", "Gemma", "Genevieve", "Georgia", "Geraldine", "Gill", "Giselle", 
        "Gladys", "Gloria", "Grace", "Griselda", "Gustave", "Gwendolyn", "Hannah", "Harriet", "Hazel", "Heather", 
        "Hedda", "Hedy", "Helen", "Heloise", "Hilda", "Hilary", "Honey", "Hulda", "Ida", "Ina", 
        "Ingrid", "Irene", "Iris", "Irma", "Isabel", "Ivy", "Jacqueline", "Jamie", "Jane", "Janet", 
        "Janice", "Jean", "Jill", "Jo", "Joa", "Joanna", "Joanne", "Jocelyn", "Jodie", "Josephine", 
        "Joyce", "Judith", "Judy", "Julia", "Julie", "Juliet", "June", "Kama", "Karen", "Katherine", 
        "Kay", "Kelly", "Kimberley", "Kitty", "Kristin", "Laura", "Laurel", "Lauren", "Lee", "Lena", 
        "Leona", "Lesley", "Letitia", "Lilith", "Lillian", "Lindsay", "Lisa", "Liz", "Lorraine", "Louise", 
        "Lydia", "Lynn", "Mabel", "Madeline", "Madge", "Maggie", "Mamie", "Mandy", "Marcia", "Margaret", 
        "Marguerite", "Maria", "Marian", "Marina", "Marjorie", "Martha", "Martina", "Mary", "Maud", "Maureen", 
        "Mavis", "Maxine", "Mag", "May", "Megan", "Melissa", "Meroy", "Meredith", "Merry", "Michelle", 
        "Michaelia", "Mignon", "Mildred", "Miranda", "Miriam", "Modesty", "Moira", "Molly", "Mona", "Monica", 
        "Muriel", "Murray", "Myra", "Myrna", "Nancy", "Naomi", "Natalie", "Natividad", "Nelly", "Nicola", 
        "Nicole", "Nina", "Nora", "Norma", "Novia", "Nydia", "Octavia", "Odelette", "Odelia", "Olga", 
        "Olive", "Olivia", "Ophelia", "Pag", "Page", "Pamela", "Pandora", "Patricia", "Paula", "Pearl", 
        "Penelope", "Penny", "Philipppa", "Phoebe", "Phoenix", "Phyllis", "Polly", "Poppy", "Prima", "Priscilla", 
        "Prudence", "Queena", "Quintina", "Rachel", "Rae", "Renata", "Renee", "Rita", "Riva", "Roberta", 
        "Rosalind", "Rose", "Rosemary", "Roxanne", "Ruby", "Ruth", "Sabina", "Sally", "Sabrina", "Salome", 
        "Samantha", "Sandra", "Sandy", "Sara", "Sarah", "Sebastiane", "Selena", "Sharon", "Sheila", "Sherry", 
        "Shirley", "Sibyl", "Sigrid", "Simona", "Sophia", "Spring", "Stacey", "Setlla", "Stephanie", "Susan", 
        "Susanna", "Susie", "Suzanne", "Tabitha", "Tammy", "Teresa", "Tess", "Thera", "Theresa", "Tiffany", 
        "Tobey", "Tracy", "Trista", "Truda", "Ula", "Una", "Ursula", "Valentina", "Valerie", "Vera", 
        "Verna", "Veromca", "Veronica", "Victoria", "Vicky", "Violet", "Virginia", "Vita", "Vivien", "Wallis", 
        "Wanda", "Wendy", "Winifred", "Winni", "Xanthe", "Xaviera", "Xenia", "Yedda", "Yetta", "Yvette", 
        "Yvonne", "Zara", "Zenobia", "Zoe", "Zona", "Zora", "zhao", "qian", "sun", "li", 
        "zhou", "wu"
    };

}
