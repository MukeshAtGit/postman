import akka.actor.{Actor,ActorSystem, Props};

class ActorExample extends Actor{
  def receive = {
    case message:String => println("Message received by "+self.path.name+": "+message);
      val childactor1 = context.actorOf(Props[ChildActor1], "ChildActor1");
      childactor1 ! "Hello child Actor 1"
      context.stop(childactor1);
    case _ => println("Unknown message");

  }
}


class ChildActor1 extends Actor{
  def receive = {
    case message:String => println("Message received by "+self.path.name+": "+message)
      val childactor2 = context.actorOf(Props[ChildActor2], "ChildActor2");
      childactor2 ! "Hello child Actor2"
    case _ => println("Unknown message")
  }
  override def preStart(){
    println("Child Actor 1 ");
  }
  override def postStop(){
    println("Child Actor 1 stop");
  }
}

class ChildActor2 extends Actor{
  def receive = {
    case message:String => println("Message received by "+self.path.name+": "+message);
    case _ => println("Unknown message");

  }
  override def postStop(){
    println("Child Actor 2 stop ");
  }
  override def preStart(){
    println("Child Actor 2");
  }
  }


object ActorExample{
  def main(args:Array[String]){
    val actorSystem = ActorSystem("ActorSystem");
    val actor = actorSystem.actorOf(Props[ActorExample], "RootActor");
    actor ! "Hello"

  }
}