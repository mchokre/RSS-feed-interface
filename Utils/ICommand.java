/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Utils;

/**
 *
 * @author mchokre
 */
public interface ICommand {
    public void save(String ID, PressRelease pressRelease);
    public void cancel();

    public void save(PressRelease currentPress);
}
