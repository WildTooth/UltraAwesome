package com.github.wildtooth.awesome.core.balance;

import com.github.wildtooth.awesome.api.balance.Balance;
import com.github.wildtooth.awesome.core.listener.chat.ChatVariables;
import com.github.wildtooth.awesome.core.listener.chat.ChatVariables.Variable;
import com.github.wildtooth.awesome.core.util.StringUtil;
import net.labymod.api.Laby;
import net.labymod.api.util.concurrent.task.Task;
import java.util.concurrent.TimeUnit;

class DefaultBalance implements Balance {

  private double balance;

  DefaultBalance(double balance) {
    this.balance = balance;
  }

  @Override
  public double getNumericValue() {
    return this.balance;
  }

  @Override
  public String getFormattedValue() {
    return StringUtil.formatNum(this.balance);
  }

  @Override
  public void refresh() {
    Laby.labyAPI().minecraft().chatExecutor().chat("/bal");
    Task.builder(() -> {
      String corrected = ChatVariables.getVariable(Variable.BALANCE)
          .replace(".", "")
          .replace(",", ".");
      this.balance = Double.parseDouble(corrected);
    }).delay(3, TimeUnit.SECONDS).build().execute();
  }
}
