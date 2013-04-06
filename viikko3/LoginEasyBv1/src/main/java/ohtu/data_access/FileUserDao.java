/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ohtu.domain.User;

/**
 *
 * @author Kati
 */
public class FileUserDao implements UserDao{

    private String fileName;
    private List<User> users;
    
    public FileUserDao(String fileName) {
        this.fileName = fileName;
        this.users = new ArrayList();
    }
    
    
    @Override
    public List<User> listAll() {
        try {
            readFile();
            return users;
        } catch (FileNotFoundException ex) {
            try {
                FileWriter fileWriter = new FileWriter(new File (fileName));
                fileWriter.close();
            } catch (IOException ex1) {
                System.out.println("File creation failed: " +ex1.getMessage());
                
            }
        }
        //jos lukeminen ei onnistunut palautetaan tyhjä lista
        return new ArrayList();
    }

    @Override
    public User findByName(String name) {
        for (User user: users) {
            if (user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
        try {
            writeFile();
        } catch (IOException ex) {
            System.out.println("Writing to a File failed " +ex.getMessage());
        }
    }
    
    public void readFile() throws FileNotFoundException{
            Scanner fileScanner = new Scanner(new File(fileName));
            users.clear();
            while (fileScanner.hasNext()){
                String next = fileScanner.next();
                String[] splitted = next.split(":");
                users.add(new User(splitted[0],splitted[1]));
            }
            fileScanner.close();
    }
    
    public void writeFile () throws IOException{
        FileWriter fileWriter = new FileWriter (new File (fileName));
        for (User user: users){
            fileWriter.write(user.getUsername()+":"+user.getPassword()+"\n");
        }
        fileWriter.close();
    }
    
}
