package guitest;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
public class gui implements ActionListener{
    
   JFrame frame= new JFrame("Logic Gate Operation");
   JTextField equation;
   JTextField a_input=new JTextField("");
   JTextField b_input=new JTextField("");
   JTextField c_input=new JTextField("");
   JTextField output=new JTextField("");
   JButton button,calculate;
   String input;
   JLabel equation_label = new JLabel("Enter Equation");
   JLabel output_label = new JLabel("Output");
   JLabel invalid_label = new JLabel("");
   JLabel input_command_label = new JLabel("Enter input");
   JLabel a_label = new JLabel("A: ");
   JLabel b_label = new JLabel("B: ");
   JLabel c_label = new JLabel("C: ");
   String valid_characters = "ABC+*^()'";
   int operation;
   boolean val,value;
   char op;

   gui(){
        equation_label.setBounds(210,50,100,30);
        equation=new JTextField("");
        equation.setBounds(200,80,105,30);
        button = new JButton("OK");
        calculate = new JButton("CALCULATE");
        button.setBounds(225,120,60,30);
        calculate.setBounds(195,340,130,30);
        button.addActionListener(this);  
        calculate.addActionListener(this);  
        frame.add(equation_label);
        frame.add(equation);
        frame.add(button);
        button.addActionListener(this);
        invalid_label.setBounds(220,150,100,30);
        invalid_label.setForeground(Color.red);
        input_command_label.setBounds(200,180,100,30);
        a_label.setBounds(200,220,20,30);
        b_label.setBounds(200,260,20,30);
        c_label.setBounds(200,300,20,30);
        a_input.setBounds(230,220,50,30);
        b_input.setBounds(230,260,50,30);
        c_input.setBounds(230,300,50,30);
        output_label.setBounds(225,370, 100,30);
        output.setBounds(215,400, 70,30);
        a_input.setEditable(false);
        b_input.setEditable(false);
        c_input.setEditable(false);
        frame.add(invalid_label);
        frame.add(input_command_label);
        frame.add(a_label);
        frame.add(b_label);
        frame.add(c_label);
        frame.add(a_input);
        frame.add(b_input);
        frame.add(c_input);
        frame.add(output_label);
        frame.add(output);
        calculate.addActionListener((ActionEvent ae) -> {
            input = equation.getText().trim();
            val = false;
            operation = 1;
            for(int i=0;i<input.length();i++){
                if(input.charAt(i) == 'A' || input.charAt(i) == 'B' || input.charAt(i) == 'C'){
                    op = input.charAt(i);
                    value = measure_value(op);
                    i++;
                    if(i==input.length()){
                        val = perform_operation(operation,val,value);
                        break;
                    }
                    if(input.charAt(i) == '\'')
                        value = !value;
                    else i--;
                    val = perform_operation(operation,val,value);
                }
                else{
                    if(input.charAt(i) == '*')operation = 0;
                    if(input.charAt(i) == '+')operation = 1;
                    if(input.charAt(i) == '^')operation = 2;
                }
            }
            output.setText(String.valueOf(val?1:0));
        });
        frame.add(calculate);
        frame.setSize(500,500);
        frame.setLayout(null);  
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public boolean measure_value(char op){
    int num;
    if(op == 'A')
        num = Integer.parseInt(a_input.getText());
    else if(op == 'B')
        num = Integer.parseInt(b_input.getText());
    else 
        num = Integer.parseInt(c_input.getText());
    if(num>0)return true;else return false;
   }
   public boolean perform_operation(int operation, boolean val, boolean value){
        switch (operation) {
            case 0:
                return perform_and(val,value);
            case 1:
                return perform_or(val,value);
            default:
                return perform_xor(val,value);
        }
   }
   public boolean perform_and(boolean val,boolean value){return val && value;}
   public boolean perform_or(boolean val,boolean value){return val || value;}
   public boolean perform_xor(boolean val,boolean value){return val ^ value;}

    @Override
   public void actionPerformed(ActionEvent e){
      input = equation.getText();
      int is_invalid = 0;
      for(int i=0;i<input.length();i++)
          if(valid_characters.indexOf(input.charAt(i)) == -1){
            invalid_label.setText("Invalid Input");
            is_invalid = 1;
            a_input.setEditable(false);
            b_input.setEditable(false);
            c_input.setEditable(false);
            break;
            }
      if(is_invalid == 0){
        invalid_label.setText("");
        if(input.indexOf('A') != -1)a_input.setEditable(true);
        else{a_input.setText("");a_input.setEditable(false);}
        if(input.indexOf('B') != -1)b_input.setEditable(true);
        else{b_input.setText("");b_input.setEditable(false);}
        if(input.indexOf('C') != -1)c_input.setEditable(true);
        else {c_input.setText("");c_input.setEditable(false);}
      }
    }
}