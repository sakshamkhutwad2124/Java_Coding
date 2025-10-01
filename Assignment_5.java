
class ChatUser extends Thread {

    private String userName;
    private boolean running = true;
    private boolean suspended = false;

    public ChatUser(String name) {
        this.userName = name;
    }

    @Override
    public void run() {
        int counter = 1;
        while (running) {
            synchronized (this) {
                while (suspended) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(userName + " says: Message " + counter++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(userName + " has stopped chatting.");
    }

    public void suspendChat() {
        suspended = true;
    }

    public synchronized void resumeChat() {
        suspended = false;
        notify();
    }

    public void stopChat() {
        running = false;
    }
}

public class Assignment_5 {

    public static void main(String[] args) {
        ChatUser user1 = new ChatUser("Aryan");
        ChatUser user2 = new ChatUser("Rohan");

        user1.setPriority(Thread.NORM_PRIORITY);
        user2.setPriority(Thread.MAX_PRIORITY);

        user1.start();
        user2.start();

        System.out.println("User1 is alive? " + user1.isAlive());
        System.out.println("User2 is alive? " + user2.isAlive());

        try {
            Thread.sleep(4000);

            System.out.println("Suspending Bob...");
            user2.suspendChat();

            Thread.sleep(3000);

            System.out.println("Resuming Bob...");
            user2.resumeChat();

            Thread.sleep(3000);

            System.out.println("Stopping all users...");
            user1.stopChat();
            user2.stopChat();

            user1.join();
            user2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("User1 alive after stop? " + user1.isAlive());
        System.out.println("User2 alive after stop? " + user2.isAlive());

        System.out.println("Chat simulation ended.");
    }
}
