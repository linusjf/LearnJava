package com.javacodegeeks.patterns.commandpattern;

public enum TestCommandPattern {
  ;

  public static void main(String[] args) {
    init();
  }

  private static void init() {
    ThreadPool pool = new ThreadPool(10);
    Email email;
    Sms sms;
    FileIO fileIO;

    Logging logging;

    for (int i = 0; i < 5; i++) {
      email = new Email();
      EmailJob emailJob = new EmailJob();
      emailJob.setEmail(email);
      sms = new Sms();
      SmsJob smsJob = new SmsJob();
      smsJob.setSms(sms);
      fileIO = new FileIO();
      FileIOJob fileIOJob = new FileIOJob();
      fileIOJob.setFileIO(fileIO);
      logging = new Logging();
      LoggingJob logJob = new LoggingJob();
      logJob.setLogging(logging);
      pool.addJob(emailJob);
      pool.addJob(smsJob);
      pool.addJob(fileIOJob);
      pool.addJob(logJob);
    }
    pool.shutdownPool();
  }
}
