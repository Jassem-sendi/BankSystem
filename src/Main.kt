class BankAccount(var id:Int?,var name:String,var currentBalance:Double){
    fun with(amountToWithdraw:Double){
        if (currentBalance<amountToWithdraw ||amountToWithdraw<=0.0   ) {
            println("Amount is more than in the main balance")
        }
        else
        {
            currentBalance-=amountToWithdraw
        }

    }
    fun deposit(amountToDeposit:Double){
        if (amountToDeposit<0 ){
            println("amount must be positive")
        }
        else{
            currentBalance+=amountToDeposit
        }
    }
    fun displayData(){
        println("Account ID: $id withe Name :$name have balance $currentBalance")
    }

}
class BankSystem(){
    private var accounts= mutableListOf<BankAccount>()
    var lastAccount=1000
    fun AccountID(){
         lastAccount++
    }
    fun createAccount(holder:String, balance:Double){
        var account=BankAccount(lastAccount,holder,balance)
        accounts.add(account)
        println("Account Create successfully ")
        AccountID()
    }
    fun findAccount(name:String):BankAccount?{
        for (account in accounts){
            if(name.toLowerCase()==account.name.toLowerCase()){
                return account
            }
        }
        return null
    }
    fun displayAccounts(){
        for(account in 0..accounts.size -1 ){
            BankAccount(accounts[account].id,accounts[account].name,accounts[account].currentBalance).displayData()
        }
    }
    fun TransferMoney(fromAccount:String,toAccount:String,amounttotransfer:Double){
        if (findAccount(fromAccount)!=null && findAccount(toAccount)!=null){

            val sender=findAccount(fromAccount)
            val reciver=findAccount(toAccount)
            if (sender?.currentBalance!!>=amounttotransfer) {
                sender.with(amounttotransfer)
                reciver?.deposit(amounttotransfer)
                println("Transfer successfully")
            }
            else{
                println("$fromAccount you dont have enough money to send to $toAccount")
            }
        }
        else{
            println(" please verify The sender or the receiver account ")
        }
    }

}
fun main(){
    val banksystem=BankSystem()
    while (true){
        println("1-create Account")
        println("2-Deposit Amount")
        println("3-Withdrawal Amount")
        println("4-Transfer money")
        println("5-Show Accounts")
        println("6-Exit")
        val choice=readln()
        when(choice.toInt()){
            1->{
                println("Account Holder")
                val accountName= readln()
                println("Account Balance")
                val accountBalance= readln()
                banksystem.createAccount(accountName,accountBalance.toDouble())
            }
            2->{
                println("enter account Name To deposit on it :  ")
                val accountNameToDeposit= readln()
                if (banksystem.findAccount(accountNameToDeposit)!=null){
                    println("enter a amount to deposit into your account with Name: $accountNameToDeposit")
                    val amountToDeposit= readln()
                    val account= banksystem.findAccount(accountNameToDeposit)
                    account?.deposit(amountToDeposit.toDouble())
                    println("Deposit successfully")

                }
                else{
                    println("Account Not Fount")
                }
            }
            3->{
                println("enter account Name To Withdraw from it :  ")
                val accountNameToWithdraw= readln()
                if (banksystem.findAccount(accountNameToWithdraw)!=null){
                    println("enter a amount to Withdraw from  your account with Name: $accountNameToWithdraw")
                    val amountToWithdraw= readln()
                    val account= banksystem.findAccount(accountNameToWithdraw)
                    account?.with(amountToWithdraw.toDouble())
                    if (amountToWithdraw.toDouble()>= 0.0 && account?.currentBalance!! > amountToWithdraw.toDouble()){
                        println("Withdraw successfully")
                    }

                }
                else{
                    println("Account Not Fount")
                }
            }
            4->{
                println("account to send From")
                val sender= readln()
                println("account to receive")
                val reciver= readln()
                println("Amount to send")
                val amount= readln()
                banksystem.TransferMoney(sender,reciver,amount.toDouble())
            }
            5->{
                banksystem.displayAccounts()
            }
            6->{
                break
            }
            else -> println("command not found")
        }

    }
}