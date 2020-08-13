package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    public Label lblFirstRoll;
    public Label lblSecondRoll;
    public Label lblMoney;
    public Label lblStatus;
    public Button btnStart;
    public Button btnHigher;
    public Button btnLower;
    public Button btnBet;
    public Button btnRestart;
    public TextField textBetAmt;
    private int fRoll;
    private int sRoll;
    private String bet;
    private double money;

    public void startGame(ActionEvent actionEvent){
    //Restart the game by resetting second roll and win status values.
    lblSecondRoll.setText("");
    lblStatus.setText("");
    //Let first roll and second roll be random numbers from 1 to 6.
    fRoll = (int) (Math.random()*6 + 1);
    sRoll = (int) (Math.random() * 6 + 1);
    //Show first roll, disable start game button, enable higher and lower buttons
    lblFirstRoll.setText(Integer.toString(fRoll));
    btnStart.setDisable(true);
    btnHigher.setDisable(false);
    btnLower.setDisable(false);
    }

    public void betHigher(ActionEvent actionEvent) {
        //set bet to higher, disable higher and lower buttons, enable bet button
        bet = "Higher";
        btnHigher.setDisable(true);
        btnLower.setDisable(false);
        btnBet.setDisable(false);
    }

    public void betLower(ActionEvent actionEvent) {
        //set bet to lower, disable higher and lower buttons, enable bet button
        bet = "Lower";
        btnHigher.setDisable(false);
        btnLower.setDisable(true);
        btnBet.setDisable(false);
    }

    public void bet(ActionEvent actionEvent) {
        //try if the bet amount can become a double. If not, then an error happens and "Invalid input for amount" appears.
        try {
            money = Double.parseDouble(lblMoney.getText());
            //If the bet amount is negative, we are not allowed to bet.
            if(Double.parseDouble(textBetAmt.getText()) >= 0) {
                //If the bet amount is smaller than the money, we are allowed to bet. If not "Cannot bet more than your current amount of money" appears.
                if (Double.parseDouble(textBetAmt.getText()) <= money) {
                    //if the user bets higher and it is higher or they bet lower and it is lower, then they won and get bet amount of money.
                    if (bet.equals("Higher") && fRoll < sRoll || bet.equals("Lower") && fRoll > sRoll) {
                        lblMoney.setText(Double.toString(money + Double.parseDouble(textBetAmt.getText())));
                        lblStatus.setText("You Won!");
                        btnStart.setDisable(false);
                    }
                    //any other cases means that the user lost and they lose their money by bet amount.
                    else {
                        lblMoney.setText(Double.toString(money - Double.parseDouble(textBetAmt.getText())));
                        money = Double.parseDouble(lblMoney.getText());
                        //If the user doesn't have any money left, then we give them a restart button option. Else, just say "You Lost!".
                        if (money == 0) {
                            lblStatus.setText("You Lost! Looks like you lost all your money");
                            btnStart.setDisable(true);
                            btnRestart.setVisible(true);
                        } else {lblStatus.setText("You Lost!");  btnStart.setDisable(false);}
                    }
                    //Show the second roll, disable bet button, lower button, and higher button, and clear bet amount.
                    lblSecondRoll.setText(Integer.toString(sRoll));
                    btnBet.setDisable(true);
                    btnLower.setDisable(true);
                    btnHigher.setDisable(true);
                    textBetAmt.clear();
                } else lblStatus.setText("Cannot bet more than your current amount of money.");
            } else lblStatus.setText("Cannot bet negative value.");
        } catch (Exception e) {lblStatus.setText("Invalid input for bet amount.");}
    }

    public void restart(ActionEvent actionEvent) {
        //Reset all values to the default value.
        btnRestart.setVisible(false);
        btnStart.setDisable(false);
        lblStatus.setText("");
        lblFirstRoll.setText("");
        lblSecondRoll.setText("");
        lblMoney.setText("100");
    }
}
