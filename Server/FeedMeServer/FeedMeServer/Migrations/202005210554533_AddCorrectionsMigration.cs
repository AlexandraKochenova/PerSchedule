namespace FeedMeServer.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddCorrectionsMigration : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Things",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Name = c.String(),
                        Code = c.Int(nullable: false),
                        Information = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
            AddColumn("dbo.Pets", "Sex", c => c.Boolean(nullable: false));
            AddColumn("dbo.Pets", "Type", c => c.String());
            AddColumn("dbo.Responsibilities", "DateCreating", c => c.Long(nullable: false));
            AddColumn("dbo.Responsibilities", "TimeDoing", c => c.Long(nullable: false));
            AddColumn("dbo.Users", "FamilyName", c => c.String());
            AlterColumn("dbo.Pets", "Age", c => c.Long(nullable: false));
            DropColumn("dbo.Responsibilities", "DateTimeResponsibility");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Responsibilities", "DateTimeResponsibility", c => c.DateTime(nullable: false));
            AlterColumn("dbo.Pets", "Age", c => c.Int(nullable: false));
            DropColumn("dbo.Users", "FamilyName");
            DropColumn("dbo.Responsibilities", "TimeDoing");
            DropColumn("dbo.Responsibilities", "DateCreating");
            DropColumn("dbo.Pets", "Type");
            DropColumn("dbo.Pets", "Sex");
            DropTable("dbo.Things");
        }
    }
}
